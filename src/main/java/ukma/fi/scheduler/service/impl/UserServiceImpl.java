package ukma.fi.scheduler.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.repository.UserRepository;
import ukma.fi.scheduler.service.UserService;

import java.util.List;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            log.info("found by id  -> id:" + id);
            return userRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public User findUserByLogin(String login) {
        if (userRepository.findByLogin(login).isPresent()) {
            log.info("found by login  -> login:" + login);
            return userRepository.findByLogin(login).get();
        } else {
            return null;
        }
    }
}
