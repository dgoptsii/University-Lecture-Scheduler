package ukma.fi.scheduler.service.interf;

import ukma.fi.scheduler.db.entities.Lesson;
import ukma.fi.scheduler.db.entities.Subject;

public interface LessonServiceInterface {

    Lesson create(Subject subject, Integer lessonNumber, Integer dayOfWeek);

    void delete(Lesson lesson);

    void edit(Lesson lesson);

}
