package ukma.fi.scheduler.service.interf;

import ukma.fi.scheduler.db.entities.User;

public interface AuthServiceInterface {

    void login(String login);

    void register(User user);
}
