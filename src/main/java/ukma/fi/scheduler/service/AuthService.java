package ukma.fi.scheduler.service;
import ukma.fi.scheduler.entities.*;


public interface AuthService {

    User login(String login, String password);

    User registration(User user);

    User getUserInfo(Long id);
}
