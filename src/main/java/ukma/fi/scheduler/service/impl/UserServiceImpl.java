package ukma.fi.scheduler.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.entities.*;
import ukma.fi.scheduler.service.*;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    //for operations with lessons
    private final LessonService scheduleService;

    //for getting info about canceled and shifted lessons
    private final ShiftService shiftService;

    @Autowired
    public UserServiceImpl(LessonService scheduleService, ShiftService shiftService) {
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

    @Override
    public void showFacultySubjects(Faculty faculty) {

    }

    @Override
    public void showFacultyLessons(Subject subject) {

    }
}
