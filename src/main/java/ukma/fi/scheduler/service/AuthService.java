package ukma.fi.scheduler.service;

import ukma.fi.scheduler.controller.dto.UserDTO;
import ukma.fi.scheduler.controller.dto.UserLoginDTO;
import ukma.fi.scheduler.entities.User;

import javax.validation.Valid;


public interface AuthService {

    User login(UserLoginDTO user);

    User registration(User user, String role);

    User editUser( UserDTO userNew, String login);

    User getUserInfo(Long id);
}
