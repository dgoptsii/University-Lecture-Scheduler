package ukma.fi.scheduler.service.impl;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.entities.*;
import ukma.fi.scheduler.service.*;

@Service
public class ShiftServiceImpl implements ShiftService {
    @Override
    public void create(Lesson lesson, Integer oldWeek,
                       Integer newWeek, Integer dayOfWeek,
                       Integer lessonNum) {

    }

    @Override
    public void edit(Shift shift) {

    }

    @Override
    public void delete(Shift shift) {

    }

    @Override
    public void cancelLesson(Lesson lesson, Integer week) {

    }
}
