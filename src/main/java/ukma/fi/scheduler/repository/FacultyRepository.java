package ukma.fi.scheduler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ukma.fi.scheduler.entities.Faculty;
import ukma.fi.scheduler.entities.User;

import java.util.List;
import java.util.Optional;
@Repository
public interface FacultyRepository extends CrudRepository<Faculty, Long> {
    Optional<Faculty> findByName(String name);
    List<Faculty> findAll();
}
