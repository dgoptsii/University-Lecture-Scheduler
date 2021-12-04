package ukma.fi.scheduler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ukma.fi.scheduler.entities.Subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {
}
