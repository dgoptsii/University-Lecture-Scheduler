package ukma.fi.scheduler.service.impl;

import com.sun.media.sound.InvalidDataException;
import javassist.NotFoundException;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.ServiceMarker;
import ukma.fi.scheduler.repository.UserRepository;
import ukma.fi.scheduler.entities.*;
import ukma.fi.scheduler.service.*;

import java.util.regex.Pattern;

@ServiceMarker
@Service
@Log4j2
public class AuthServiceImpl implements AuthService {

    //for searching users in DB
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Override
    public User login(String login, String password) {
        User userInDb = userService.findUserByLogin(login);
        if(userInDb==null){
            try {
                throw new NotFoundException("User not found.");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        if(!userInDb.getPassword().equals(password)){
            try {
                throw new InvalidDataException("Incorrect password.");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        log.info("Login successfully -> " + login);
        return userInDb;
    }

    //use naukma e-mail
    @Override
    public User registration(User user) {
        // add validations
        Pattern email = Pattern.compile("([a-z]+\\.[a-z]+)@ukma\\.edu\\.ua");
        Pattern status = Pattern.compile("STUDENT|ADMIN|TEACHER");
        if (!email.matcher(user.getLogin()).matches()) {
            try {
                throw new InvalidDataException("Invalid email.");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        if(user.getPassword().length()<8){
            try {
                throw new InvalidDataException("Password shorter then 8 symbols.");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }

        User userInDb = userService.findUserByLogin(user.getLogin());
        if(userInDb!=null){
            try {
                throw new NotFoundException("User with this login is exist.");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }

        if(!status.matcher(user.getStatus()).matches()){
            try {
                throw new InvalidDataException("Invalid status.");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        log.info("registration -> user:" + user.getLogin() );
        return userRepository.save(user);
    }

    @Override
    public User getUserInfo(Long id) {
        User userInDb = userService.findUserById(id);
        if(userInDb==null){
            try {
                throw new NotFoundException("User not found.");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        log.info("get user info -> user id:" + id );
        return userInDb;
    }

}
