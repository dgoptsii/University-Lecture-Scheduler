package ukma.fi.scheduler.service;
import ukma.fi.scheduler.entities.*;

public interface LessonService {

    Lesson create(Subject subject, Integer lessonNumber, Integer dayOfWeek);

    void delete(Lesson lesson);

    void edit(Lesson lesson);

    void show(Long id);
}
