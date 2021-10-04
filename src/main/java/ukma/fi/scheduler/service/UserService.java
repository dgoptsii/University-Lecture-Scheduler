package ukma.fi.scheduler.service;
import ukma.fi.scheduler.entities.*;

import java.util.List;

public interface UserService {

    User findUserById(Long id);

    User findUserByLogin(String login);

    List<Lesson> getUserLessons(User user);

    List<Lesson> getUserLessonsForWeek(User user, Integer week);

    void addLessonToUser(User user, Lesson lesson);

    void showFacultySubjects(Faculty faculty);

    void showFacultyLessons(Subject subject);
}
