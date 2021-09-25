package ukma.fi.scheduler.service.interf;

import ukma.fi.scheduler.db.entities.Lesson;
import ukma.fi.scheduler.db.entities.User;

import java.util.List;

public interface UserServiceInterface {

    User findUserById(Long id);

    User findUserByLogin(String login);

    List<Lesson> getUserLessons(User user);

    List<Lesson> getUserLessonsForWeek(User user, Integer week);

    void addLessonToUser(User user, Lesson lesson);
}
