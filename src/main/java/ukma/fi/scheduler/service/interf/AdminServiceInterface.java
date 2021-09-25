package ukma.fi.scheduler.service.interf;

import ukma.fi.scheduler.db.entities.Lesson;
import ukma.fi.scheduler.db.entities.Subject;

public interface AdminServiceInterface {

    Lesson createLesson(Subject subject, Integer lessonNumber, Integer dayOfWeek);

    void deleteLesson(Lesson lesson);

    void editLesson(Lesson lesson);
}
