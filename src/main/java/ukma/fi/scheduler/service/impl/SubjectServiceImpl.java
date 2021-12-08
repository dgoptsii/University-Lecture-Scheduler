package ukma.fi.scheduler.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.controller.dto.LessonDTO;
import ukma.fi.scheduler.controller.dto.SubjectLectureDTO;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.exceptionHandlers.exceptions.InvalidData;
import ukma.fi.scheduler.exceptionHandlers.exceptions.SubjectNotFoundException;
import ukma.fi.scheduler.repository.SubjectRepository;
import ukma.fi.scheduler.service.LessonService;
import ukma.fi.scheduler.service.SubjectService;
import ukma.fi.scheduler.service.UserService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Service
@Log4j2
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private LessonService lessonService;

    @Override
    public Subject findSubjectById(Long id) {
        if (subjectRepository.findById(id).isPresent()) {
            log.info("found by id  -> id:" + id);
            return subjectRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Subject findSubjectByName(String name) {
        if (subjectRepository.findSubjectByName(name).isPresent()) {
            log.info("found subject by name -> name:" + name);
            return subjectRepository.findSubjectByName(name).get();
        } else {
            return null;
        }
    }

    @Override
    public List<Subject> findSubjectByIdIn(List<Long> id) {
        return subjectRepository.findSubjectsByIdIn(id);
    }

    @Override
    public Subject create(@Valid SubjectLectureDTO dto) {
        Subject subject = new Subject(dto.getName(), dto.getMaxGroups(), dto.getSpecialty(), dto.getYear());
        if (subjectRepository.findSubjectByName(subject.getName()).isPresent()) {
            throw new InvalidData(Collections.singletonMap("name", subject.getName()));
        }
        subjectRepository.save(subject);
        LessonDTO lecture = new LessonDTO(subject,dto.getTeacher(),dto.getDayOfWeek(),dto.getLessonNumber(),0);
        lessonService.create(lecture);
        log.info("created subject -> name:" + subject.getName());
        return findSubjectByName(subject.getName());
    }

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public void deleteSubject(Long id) {
        if (!subjectRepository.findById(id).isPresent()) {
            throw new SubjectNotFoundException("Subject with id: "+id+" not found.");
        } else {
            subjectRepository.delete(subjectRepository.findById(id).get());
        }
    }

}
