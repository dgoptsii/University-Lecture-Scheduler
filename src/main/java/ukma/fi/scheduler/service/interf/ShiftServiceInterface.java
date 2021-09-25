package ukma.fi.scheduler.service.interf;

import ukma.fi.scheduler.db.entities.Lesson;
import ukma.fi.scheduler.db.entities.Shift;

public interface ShiftServiceInterface {

    void create(Lesson lesson, Integer oldWeek,
                Integer newWeek, Integer dayOfWeek, Integer lessonNum);

    void edit(Shift shift);

    void delete(Shift shift);

    void cancel(Lesson lesson, Integer week);
}
