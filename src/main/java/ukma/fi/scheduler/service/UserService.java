package ukma.fi.scheduler.service;

import ukma.fi.scheduler.entities.User;

public interface UserService {

    User findUserById(Long id);

    User findUserByLogin(String login);
}
