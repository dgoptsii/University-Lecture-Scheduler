package ukma.fi.scheduler.service.impl;

import org.springframework.stereotype.Service;
import ukma.fi.scheduler.db.entities.Lesson;
import ukma.fi.scheduler.db.entities.Subject;
import ukma.fi.scheduler.service.interf.LessonServiceInterface;

@Service
public class LessonService implements LessonServiceInterface {
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
}
