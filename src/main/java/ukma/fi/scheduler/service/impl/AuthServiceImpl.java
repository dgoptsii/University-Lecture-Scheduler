package ukma.fi.scheduler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.service.AuthService;
import ukma.fi.scheduler.service.UserService;

@Service
public class AuthServiceImpl implements AuthService {

    //for searching users in DB
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void login(String login, String password) {

    }

    //use naukma e-mail
    @Override
    public void registration(User user) {

    }

    @Override
    public void getUserInfo(Long id) {

    }

}
