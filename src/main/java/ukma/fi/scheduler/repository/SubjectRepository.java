package ukma.fi.scheduler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ukma.fi.scheduler.entities.Subject;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {
    List<Subject> findSubjectsBySpecialityAndYear(String speciality, Integer year);
    List<Subject> findSubjectsByIdNotIn(List<Long> id);
    Optional<Subject> findSubjectByName(String name);
    List<Subject> findAll();
}
