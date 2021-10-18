package ukma.fi.scheduler.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.repository.UserRepository;
import ukma.fi.scheduler.service.AuthService;

@RestController
@Log4j2
public class TestController {


    @Autowired
    private AuthService authService;

    @GetMapping("/lol")
    public User login(){
        log.trace("some trace logging...");
        log.debug("some debug logging...");
        log.info("some info logging...");
        log.warn("some warn logging...");
        log.error("some error logging...");
        return authService.getUserInfo(1l);
    }

}
