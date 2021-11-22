package ukma.fi.scheduler.service;

import ukma.fi.scheduler.controller.dto.UserLoginDTO;
import ukma.fi.scheduler.entities.User;


public interface AuthService {

    User login(UserLoginDTO user);

    User registration(User user);

    User getUserInfo(Long id);
}
