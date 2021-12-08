package ukma.fi.scheduler.repository;

import org.springframework.data.repository.CrudRepository;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;

import java.util.List;
import java.util.Set;

public interface LessonRepository extends CrudRepository<Lesson, Long> {

    List<Lesson> findBySubjectAndGroupNumber(Subject subject, Integer groupNumber);

    List<Lesson> findLessonsBySubjectInAndGroupNumber(List<Subject> subject, Integer groupNumber);
    List<Lesson> findLessonsBySubjectIn(Set<Subject> subject);
    List<Lesson> findByTeacherLogin(String login);
    List<Lesson> findAll();
}
