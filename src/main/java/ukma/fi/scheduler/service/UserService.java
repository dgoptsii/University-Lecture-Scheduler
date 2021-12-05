package ukma.fi.scheduler.service;

import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;

import java.util.List;

public interface UserService {

    User findUserById(Long id);

    User findUserByLogin(String login);

    List<User> findByRole(String role);

    List<Subject> findNormativeSubjects(String login);

    List<Subject>  findNonNormativeSubjectsForUser(String login);

    List<Subject> findNonNormativeFreeSubjects(String login);

    List<Subject> findNonNormativeSubjects(String login);
}
