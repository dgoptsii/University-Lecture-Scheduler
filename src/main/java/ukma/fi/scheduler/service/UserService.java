package ukma.fi.scheduler.service;

import ukma.fi.scheduler.controller.dto.SubjectGroupListDTO;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;

import java.util.List;

public interface UserService {

    User findUserById(Long id);

    User findUserByLogin(String login);

    List<User> findByRole(String role);

    void editSubjectGroup(String login,SubjectGroupListDTO form);

    SubjectGroupListDTO getSubjectGroupDTOS(String login);

    List<Subject> findNormativeSubjects(String login);

    List<Subject>  findNonNormativeSubjectsForUser(String login);

    List<Subject> findNonNormativeFreeSubjects(String login);

    List<Subject> findNonNormativeSubjects(String login);

    List<User> findAllStudents();

    List<User> findAllTeachers();
}
