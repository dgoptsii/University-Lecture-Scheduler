package ukma.fi.scheduler.service.impl;

import org.springframework.stereotype.Service;
import ukma.fi.scheduler.db.entities.Lesson;
import ukma.fi.scheduler.db.entities.Shift;
import ukma.fi.scheduler.service.interf.ShiftServiceInterface;

@Service
public class ShiftService implements ShiftServiceInterface {
    @Override
    public void create(Lesson lesson, Integer oldWeek, Integer newWeek, Integer dayOfWeek, Integer lessonNum) {

    }

    @Override
    public void edit(Shift shift) {

    }

    @Override
    public void delete(Shift shift) {

    }

    @Override
    public void cancel(Lesson lesson, Integer week) {

    }
}
