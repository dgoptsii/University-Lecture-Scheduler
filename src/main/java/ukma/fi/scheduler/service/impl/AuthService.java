package ukma.fi.scheduler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.db.entities.User;
import ukma.fi.scheduler.service.interf.AuthServiceInterface;
import ukma.fi.scheduler.service.interf.UserServiceInterface;

@Service
public class AuthService implements AuthServiceInterface {

    private UserServiceInterface userService;

    @Override
    public void login(String login) {

    }

    @Override
    public void register(User user) {

    }


    @Autowired
    public void setUserService(UserServiceInterface userService) {
        this.userService = userService;
    }
}
