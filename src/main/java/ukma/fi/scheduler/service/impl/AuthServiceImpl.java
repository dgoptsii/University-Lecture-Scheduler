package ukma.fi.scheduler.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.controller.dto.UserDTO;
import ukma.fi.scheduler.controller.dto.UserLoginDTO;
import ukma.fi.scheduler.exceptionHandlers.exceptions.InvalidData;
import ukma.fi.scheduler.exceptionHandlers.exceptions.UserNotFoundException;
import ukma.fi.scheduler.repository.UserRepository;
import ukma.fi.scheduler.entities.*;
import ukma.fi.scheduler.service.*;

import java.util.Collections;import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Valid;


@Service
@Log4j2
public class AuthServiceImpl implements AuthService {

    //for searching users in DB
    @Autowired
    private UserService userService;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(UserLoginDTO user) {
        User userInDb = userService.findUserByLogin(user.getLogin());
        if (userInDb == null) {
            throw new UserNotFoundException("User not found.");
        }
        if (!userInDb.getPassword().equals(user.getPassword())) {
            throw new InvalidData(Collections.singletonMap("password",user.getPassword()));
        }
        log.info("Login successfully -> " + user.getLogin());
        return userInDb;
    }

    //use naukma e-mail
    @Override
    public User registration(@Valid User user, String role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(role);
        return userRepository.save(user);
    }

    @Override
    public User editUser(@Valid UserDTO userNew,String login) {
        User user = userService.findUserByLogin(login);
        if(userNew.getPassword().isEmpty()){
            user.changeUser(userNew);
        }else if (!passwordEncoder.encode(userNew.getPassword()).equals(user.getPassword())){
            userNew.setPassword(passwordEncoder.encode(userNew.getPassword()));
            user.changeUser(userNew);
        }
        return userRepository.save(user);
    }


    @Override
    public User getUserInfo(Long id) {
        User userInDb = userService.findUserById(id);
        if (userInDb == null) {
            throw new UserNotFoundException(id);
        }
        log.info("get user info -> user id:" + id);
        return userInDb;
    }
}
