package ukma.fi.scheduler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.db.entities.Lesson;
import ukma.fi.scheduler.db.entities.User;
import ukma.fi.scheduler.service.interf.LessonServiceInterface;
import ukma.fi.scheduler.service.interf.ShiftServiceInterface;
import ukma.fi.scheduler.service.interf.UserServiceInterface;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    private final LessonServiceInterface scheduleService;

    private final ShiftServiceInterface shiftService;

    @Autowired
    public UserService(LessonServiceInterface scheduleService, ShiftServiceInterface shiftService) {
        this.scheduleService = scheduleService;
        this.shiftService = shiftService;
    }

    @Override
    public User findUserById(Long id) {
        return null;
    }

    @Override
    public User findUserByLogin(String login) {
        return null;
    }

    @Override
    public List<Lesson> getUserLessons(User user) {
        List<Lesson> lessons = null;

        return lessons;
    }

    @Override
    public List<Lesson> getUserLessonsForWeek(User user, Integer week) {
        return null;
    }

    @Override
    public void addLessonToUser(User user, Lesson lesson) {
        //TODO add lesson to user's list
    }
}
