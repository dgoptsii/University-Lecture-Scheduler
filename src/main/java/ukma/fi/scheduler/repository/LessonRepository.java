package ukma.fi.scheduler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ukma.fi.scheduler.entities.Lesson;

import java.util.List;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Long> {

    List<Lesson> findAllBySubject(Long id);

}
