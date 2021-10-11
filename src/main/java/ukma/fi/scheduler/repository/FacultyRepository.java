package ukma.fi.scheduler.repository;

import org.springframework.data.repository.CrudRepository;
import ukma.fi.scheduler.entities.User;

import java.util.Optional;

public interface FacultyRepository extends CrudRepository<FacultyRepository, Long> {
}
