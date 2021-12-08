package ukma.fi.scheduler.service.impl;

import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.controller.dto.LessonDTO;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.exceptionHandlers.exceptions.LessonNotFoundException;
import ukma.fi.scheduler.repository.LessonRepository;
import ukma.fi.scheduler.service.LessonService;

import javax.validation.Valid;
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
}
