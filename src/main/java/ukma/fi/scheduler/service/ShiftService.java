package ukma.fi.scheduler.service;
import ukma.fi.scheduler.entities.*;

public interface ShiftService {

    void create(Lesson lesson, Integer oldWeek,
                Integer newWeek, Integer dayOfWeek, Integer lessonNum);

    void edit(Shift shift);

    void delete(Shift shift);

    void cancelLesson(Lesson lesson, Integer week);
}
