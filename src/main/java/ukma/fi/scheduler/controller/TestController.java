package ukma.fi.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.repository.UserRepository;
import ukma.fi.scheduler.service.AuthService;

@RestController
public class TestController {

    @Autowired
    private AuthService authService;

    @GetMapping("/lol")
    public User login(){
        return authService.getUserInfo(1l);
    }

}
