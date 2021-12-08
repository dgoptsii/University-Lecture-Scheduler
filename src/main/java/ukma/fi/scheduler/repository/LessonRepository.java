package ukma.fi.scheduler.repository;

import org.springframework.data.repository.CrudRepository;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LessonRepository extends CrudRepository<Lesson, Long> {

    Optional<Lesson> findBySubjectAndGroupNumber(Subject subject, Integer groupNumber);
    List<Lesson> findLessonsByGroupNumber(Integer groupNumber);
    List<Lesson> findLessonsByGroupNumberNot(Integer groupNumber);
    List<Lesson> findLessonsBySubject_Id(Long subject_id);
    List<Lesson> findByTeacherLogin(String login);
    List<Lesson> findAll();
}
