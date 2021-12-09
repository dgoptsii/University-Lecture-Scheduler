package ukma.fi.scheduler.service.impl;

import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.controller.dto.LessonDTO;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.exceptionHandlers.exceptions.InvalidData;
import ukma.fi.scheduler.exceptionHandlers.exceptions.LessonDeleteException;
import ukma.fi.scheduler.exceptionHandlers.exceptions.LessonNotFoundException;
import ukma.fi.scheduler.exceptionHandlers.exceptions.SubjectNotFoundException;
import ukma.fi.scheduler.repository.LessonRepository;
import ukma.fi.scheduler.service.LessonService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    LessonRepository lessonRepository;

    @Override
    public Lesson create(LessonDTO dto) throws Exception {
        if (lessonRepository
                .findBySubjectAndGroupNumberAndDayOfWeekAndLessonNumber(
                        dto.getSubject(), dto.getGroupNumber(),dto.getDayOfWeek(),dto.getLessonNumber()).isPresent()) {
                throw new InvalidDataException("This lesson already exist");
        }
        if (dto.getGroupNumber() > dto.getSubject().getMaxGroups()) {
            throw new InvalidData(Collections.singletonMap("lesson_group_number", dto.getGroupNumber().toString()));
        }
        return lessonRepository.save(Lesson.createFromDto(dto));
    }

    @Override
    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    @Override
    public List<Lesson> findAllBySubject_Id(Long id) {
        return lessonRepository.findLessonsBySubject_Id(id);
    }

    @Override
    public Lesson findById(Long id){
        if (!lessonRepository.findById(id).isPresent()) {
            throw new LessonNotFoundException(id);
        }
        return lessonRepository.findById(id).get();
    }

    @Override
    public void edit(Long id, Lesson lesson) throws Exception {
        Optional<Lesson> old = lessonRepository.findById(id);
        if (!old.isPresent()) {
            throw new LessonNotFoundException(id);
        }
        if (!old.get().getId().equals(lesson.getId())) {
            throw new InvalidData(Collections.singletonMap("lesson_id", id.toString()));
        }
        if (!old.get().equals(lesson)) {
            if (lessonRepository
                    .findBySubjectAndGroupNumberAndDayOfWeekAndLessonNumber(
                            lesson.getSubject(), lesson.getGroupNumber(),lesson.getDayOfWeek(),lesson.getLessonNumber()).isPresent()) {
                throw new InvalidDataException("This lesson already exist");
            }
            if (lesson.getGroupNumber() > old.get().getSubject().getMaxGroups()) {
                throw new InvalidData(Collections.singletonMap("lesson_group_number", lesson.getGroupNumber().toString()));
            }
            lessonRepository.save(lesson);
        }
    }

    @Override
    public void delete(Long id) throws Exception{
        Optional<Lesson> lessonOptional = lessonRepository.findById(id);
        if (!lessonOptional.isPresent()) {
            throw new LessonNotFoundException(id);
        }
        Lesson lesson = lessonOptional.get();
        if (lesson.getGroupNumber() == 0) {
            throw new LessonDeleteException();
        }
        lessonRepository.delete(lesson);
    }
}
