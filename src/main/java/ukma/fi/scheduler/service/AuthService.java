package ukma.fi.scheduler.service;

import ukma.fi.scheduler.entities.User;

public interface AuthService {

    void login(String login, String password);

    void registration(User user);

    void getUserInfo(Long id);
}
