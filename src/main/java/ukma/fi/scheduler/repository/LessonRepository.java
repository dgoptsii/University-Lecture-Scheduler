package ukma.fi.scheduler.repository;

import org.springframework.data.repository.CrudRepository;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;

import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Long> {

    List<Lesson> findBySubjectAndGroupNumber(Subject subject, Integer groupNumber);

    List<Lesson> findLessonsBySubjectInAndGroupNumber(List<Subject> subject, Integer groupNumber);
    List<Lesson> findByTeacherLogin(String login);
}
