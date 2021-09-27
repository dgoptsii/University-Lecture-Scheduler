package ukma.fi.scheduler.service.impl;

import org.springframework.stereotype.Service;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {
    @Override
    public Lesson create(Subject subject, Integer lessonNumber, Integer dayOfWeek) {
        return null;
    }

    @Override
    public void delete(Lesson lesson) {

    }

    @Override
    public void edit(Lesson lesson) {

    }

    @Override
    public void show(Long id) {

    }
}
