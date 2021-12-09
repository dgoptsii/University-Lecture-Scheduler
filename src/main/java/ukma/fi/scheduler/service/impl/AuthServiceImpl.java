package ukma.fi.scheduler.service.impl;

import com.sun.media.sound.InvalidDataException;
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

    //use naukma e-mail
    @Override
    public User registration(@Valid User user, String role) throws InvalidDataException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(role);
        User res;
        try {
            res = userRepository.save(user);
        }catch (Exception ex){
            throw new InvalidDataException("User with this login already exist");
        }
        return res;
    }

    @Override
    public User editUser(@Valid UserDTO userNew,String login) throws InvalidDataException {
        System.out.println(login);
        User user = userService.findUserByLogin(login);
        if(userNew.getPassword().isEmpty()){
            user.changeUser(userNew);
        }else if (!passwordEncoder.encode(userNew.getPassword()).equals(user.getPassword())){
            userNew.setPassword(passwordEncoder.encode(userNew.getPassword()));
            user.changeUser(userNew);
        }
        User res;
        try {
            res = userRepository.save(user);
        }catch (Exception ex){
            throw new InvalidDataException("User with this login already exist");
        }
        return res;
    }

}
