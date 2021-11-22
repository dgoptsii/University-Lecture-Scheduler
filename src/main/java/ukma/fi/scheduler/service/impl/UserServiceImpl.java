package ukma.fi.scheduler.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.ServiceMarker;
import ukma.fi.scheduler.entities.Faculty;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.repository.UserRepository;
import ukma.fi.scheduler.service.LessonService;
import ukma.fi.scheduler.service.ShiftService;
import ukma.fi.scheduler.service.UserService;

import java.util.List;

@ServiceMarker
@Service
@Log4j2
public class UserServiceImpl implements UserService {

    //for operations with lessons
    @Autowired
    private LessonService scheduleService;

    //for getting info about canceled and shifted lessons
    @Autowired
    private ShiftService shiftService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            log.info("found by id  -> id:" + id);
            return userRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public User findUserByLogin(String login) {
        if (userRepository.findByLogin(login).isPresent()) {
            log.info("found by login  -> login:" + login);
            return userRepository.findByLogin(login).get();
        } else {
            return null;
        }
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
