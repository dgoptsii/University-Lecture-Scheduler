package ukma.fi.scheduler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.repository.LessonRepository;
import ukma.fi.scheduler.service.LessonService;

import javax.validation.Valid;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    LessonRepository lessonRepository;

    @Override
    public Lesson create(@Valid Lesson lesson) {
        return lessonRepository.save(lesson);
    }
}
