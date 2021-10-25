package ukma.fi.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ukma.fi.scheduler.controller.dto.UserDTO;
import ukma.fi.scheduler.controller.dto.UserLoginDTO;
import ukma.fi.scheduler.service.AuthService;
import ukma.fi.scheduler.service.impl.ConverterDtoObject;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public void login(@Valid @RequestBody UserLoginDTO user) {
        authService.login(user);
    }

    @PostMapping("/registration")
    public void registration(@Valid @RequestBody UserDTO user) {
        authService.registration(ConverterDtoObject.createUserFromDTO(user));
    }
}
