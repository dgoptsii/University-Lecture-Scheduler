package ukma.fi.scheduler.service.impl;

import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.controller.dto.LessonDTO;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.exceptionHandlers.exceptions.InvalidData;
import ukma.fi.scheduler.exceptionHandlers.exceptions.LessonNotFoundException;
import ukma.fi.scheduler.exceptionHandlers.exceptions.SubjectNotFoundException;
import ukma.fi.scheduler.repository.LessonRepository;
import ukma.fi.scheduler.service.LessonService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    LessonRepository lessonRepository;

    @Override
    public Lesson create(LessonDTO dto) {
        if(lessonRepository.findBySubjectAndGroupNumber(dto.getSubject(),dto.getGroupNumber()).isPresent()){
            try {
                throw new InvalidDataException("This lesson already exist");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
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
    public Lesson findById(Long id) {
        if(!lessonRepository.findById(id).isPresent()){
            throw new LessonNotFoundException(id);
        }
        return lessonRepository.findById(id).get();
    }

    @Override
    public void edit(Long id, Lesson lesson) {
        Lesson old = findById(id);
        if(!old.getId().equals(lesson.getId())){
            throw new InvalidData(Collections.singletonMap("lesson_id",id.toString()));
        }
        if(!old.equals(lesson)){
            System.out.println("toDelete: "+lesson);
            lessonRepository.save(lesson);
        }
    }

    @Override
    public void delete(Long id) {
        if(!lessonRepository.findById(id).isPresent()){
            throw new LessonNotFoundException(id);
        }
        lessonRepository.delete(lessonRepository.findById(id).get());
    }
}
