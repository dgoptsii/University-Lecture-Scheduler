package ukma.fi.scheduler.service.impl;

import com.sun.media.sound.InvalidDataException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.exceptionHandlers.exceptions.SubjectNotFoundException;
import ukma.fi.scheduler.service.Converters;
import ukma.fi.scheduler.controller.dto.SubjectGroupDTO;
import ukma.fi.scheduler.controller.dto.SubjectGroupListDTO;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.repository.SubjectRepository;
import ukma.fi.scheduler.repository.UserRepository;
import ukma.fi.scheduler.service.SubjectService;
import ukma.fi.scheduler.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Converters converter;

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SubjectService subjectService;

    @Override
    public User findUserById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            log.info("found by id  -> id:" + id);
            return userRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public User findUserByLogin(String login) {
        if (userRepository.findByLogin(login).isPresent()) {
            log.info("found by login  -> login:" + login);
            return userRepository.findByLogin(login).get();
        } else {
            return null;
        }
    }

    @Override
    public List<User> findByRole(String role) {
        return userRepository.findUsersByStatus(role);
    }

    @Override
    public void editSubjectGroup(String login, SubjectGroupListDTO form) {
        User user = findUserByLogin(login);
        Map<Subject, Integer> newSubGroupNum = converter.convertSubGroupDto(form);
        System.out.println(newSubGroupNum);
        newSubGroupNum.forEach((k, v) -> {
            if (v == 0 && user.getGroups().get(k) != null) {
                user.getGroups().put(k, 0);
            }
        });
        Map<Subject, Integer> update = newSubGroupNum.entrySet().stream()
                .filter(x -> x.getValue() != 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        user.getGroups().putAll(update);
        userRepository.save(user);
    }

    @Override
    public void addNonNormativeGroup(String login, Long id) {
        User user = findUserByLogin(login);
        Subject newSub = subjectService.findSubjectById(id);
        if (newSub == null) {
            throw new SubjectNotFoundException("Subject with id = " + id + " not found.");
        }
        if (user.getGroups().containsKey(newSub)) {
            try {
                throw new InvalidDataException("User already have this subject.");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        } else {
            user.getGroups().put(newSub, 0);
        }
        userRepository.save(user);
    }

    @Override
    public void deleteNonNormativeGroup(String login, Long id) {
        User user = findUserByLogin(login);
        Subject newSub = subjectService.findSubjectById(id);
        if (newSub == null) {
            throw new SubjectNotFoundException("Subject with id = " + id + " not found.");
        }
        if (!user.getGroups().containsKey(newSub)) {
            try {
                throw new InvalidDataException("User already doesn't have this subject.");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        } else {
            user.getGroups().remove(newSub);
        }
        userRepository.save(user);
    }

    @Override
    public void moveIfGroupsCountChange(Integer moveFrom,Subject sub) {
        List<User> students = findAllStudents();
        students.stream().filter(s -> s.getGroups().containsKey(sub) && s.getGroups().get(sub).equals(moveFrom)).collect(Collectors.toList());
        students.forEach(s -> s.getGroups().replace(sub,0));
        userRepository.saveAll(students);
    }

    // Find all subjects that is normative for student (login)
    @Override
    public List<Subject> findNormativeSubjects(String login) {
        User student = findUserByLogin(login);
        List<Subject> normative = subjectRepository.findSubjectsBySpecialityAndYear(student.getSpeciality(), student.getYear());
        System.out.println(normative);
        return normative;
    }

    //Find all subjects that is not normative for student (login) - with the subjects that he\her already has
    @Override
    public List<Subject> findNonNormativeSubjectsForUser(String login) {
        List<Long> normative = findNormativeSubjects(login).stream().map(Subject::getId).collect(Collectors.toList());
        return subjectRepository.findSubjectsByIdNotIn(normative);
    }

    //Find all not normative subjects that student already has
    @Override
    public List<Subject> findNonNormativeSubjects(String login) {
        User student = findUserByLogin(login);
        List<Subject> normative = findNormativeSubjects(login);
        List<Subject> studentSubjects = student.getStudentSubjects();
        studentSubjects.removeAll(normative);
        return studentSubjects;
    }


    @Override
    public List<User> findAllStudents() {
        return userRepository.findUsersByStatus("STUDENT");
    }


    @Override
    public List<User> findAllTeachers() {
        return userRepository.findUsersByStatus("TEACHER");
    }

    //Find all not normative subjects that student can add to his list
    @Override
    public List<Subject> findNonNormativeFreeSubjects(String login) {
        User student = findUserByLogin(login);
        List<Long> normative = findNormativeSubjects(login).stream().map(Subject::getId).collect(Collectors.toList());
        List<Long> studentSubjects = student.getStudentSubjectsId();
        normative.addAll(studentSubjects);
        return subjectRepository.findSubjectsByIdNotIn(normative);
    }

    @Override
    public SubjectGroupListDTO getSubjectGroupDTOS(String login) {
        List<Subject> normativeSubjects = findNormativeSubjects(login);
        List<Subject> notNormativeSubjects = findNonNormativeSubjects(login);
        User user = findUserByLogin(login);
        Map<Subject, Integer> subGroupNum = user.getGroups();

        List<SubjectGroupDTO> normativeDto = new ArrayList<>();
        normativeSubjects.forEach(el -> {
            Integer groupNum = subGroupNum.get(el);
            normativeDto.add(new SubjectGroupDTO(el.getName(), el.getId(), groupNum, el.getMaxGroups(), true));
        });

        List<SubjectGroupDTO> nonNormativeDto = new ArrayList<>();
        notNormativeSubjects.forEach(el -> {
            Integer groupNum = subGroupNum.get(el);
            nonNormativeDto.add(new SubjectGroupDTO(el.getName(), el.getId(), groupNum, el.getMaxGroups(), false));
        });

        SubjectGroupListDTO fromData = new SubjectGroupListDTO();
        fromData.addAllDto(normativeDto);
        fromData.addAllDto(nonNormativeDto);
        return fromData;
    }


}
