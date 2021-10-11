package ukma.fi.scheduler.service;
import ukma.fi.scheduler.entities.*;

public interface ShiftService {

    Shift createShift(Long OldLessonId, Integer newWeek, String dayOfWeek, Integer lessonNum);

    Shift edit(Shift shift);

    boolean delete(Long id);

    Shift cancelLesson(Long lessonId, Integer week);
}
