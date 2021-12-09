package ukma.fi.scheduler.service;

import com.sun.media.sound.InvalidDataException;
import ukma.fi.scheduler.controller.dto.UserDTO;
import ukma.fi.scheduler.entities.User;


public interface AuthService {

    User registration(User user, String role) throws InvalidDataException;

    User editUser(UserDTO userNew, String login) throws InvalidDataException;

}
