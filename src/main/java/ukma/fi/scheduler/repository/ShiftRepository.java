package ukma.fi.scheduler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ukma.fi.scheduler.entities.Shift;

import java.util.Optional;

@Repository
public interface ShiftRepository extends CrudRepository<Shift, Long> {
    Optional<Shift> findByLesson_IdAndAndWeekNumberAndIsCancelEquals(Long id, Long numb, String res);
}
