package ukma.fi.scheduler.service.impl;

import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.repository.SubjectRepository;
import ukma.fi.scheduler.repository.UserRepository;
import ukma.fi.scheduler.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

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
    public List<Subject> findNormativeSubjects(String login) {
        User student = findUserByLogin(login);
        List<Subject> normative = subjectRepository.findSubjectsBySpecialityAndYear(student.getSpeciality(), student.getYear());
        System.out.println(normative);
        return normative;
    }

    @Override
    public List<Subject> findNonNormativeSubjectsForUser(String login) {
        List<Long> normative = findNormativeSubjects(login).stream().map(Subject::getId).collect(Collectors.toList());
        return subjectRepository.findSubjectsByIdNotIn(normative);
    }

    @Override
    public List<Subject> findNonNormativeSubjects(String login) {
        User student = findUserByLogin(login);
        List<Subject> normative = findNormativeSubjects(login);
        List<Subject> studentSubjects = student.getStudentSubjects();
        studentSubjects.removeAll(normative);
        return studentSubjects;
    }

    @Override
    public List<Subject> findNonNormativeFreeSubjects(String login) {
        User student = findUserByLogin(login);
        List<Long> normative = findNormativeSubjects(login).stream().map(Subject::getId).collect(Collectors.toList());
        List<Long> studentSubjects = student.getStudentSubjectsId();
        normative.addAll(studentSubjects);
        return subjectRepository.findSubjectsByIdNotIn(normative);
    }
}
