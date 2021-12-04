package ukma.fi.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ukma.fi.scheduler.controller.dto.UserDTO;
import ukma.fi.scheduler.service.AuthService;

import javax.validation.Valid;

@Controller
@Validated
public class AuthController {

    @Autowired
    private AuthService authService;

//    @PostMapping("/login")
//    public void login(@Valid @RequestBody UserLoginDTO user) {
//        authService.login(user);
//    }

    @PostMapping("/registration")
    public void registration(@Valid @RequestBody UserDTO user) {
//        authService.registration(ConverterDtoObject.createUserFromDTO(user));
    }

    @GetMapping("/success")
    public String successLogin() {
        return "!!! SUCCESSES LOGIN !!!";
    }

    @GetMapping("/fail")
    public String failLogin() {
        return "... FAIL LOGIN ...";
    }
}
