package ukma.fi.scheduler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByLogin(String login);

    List<User> findUsersByStatus(String status);
    List<User> findAllByStatus(String status);

//    List<User> findUsersByStatusAndStudentSubjects(String status,Subject subjects);

}
