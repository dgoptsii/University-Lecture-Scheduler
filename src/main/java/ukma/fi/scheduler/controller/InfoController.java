package ukma.fi.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.service.AuthService;


@RestController
@RequestMapping("/info")
@Validated
public class InfoController {

    @Autowired
    private AuthService authService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return authService.getUserInfo(id);
    }
}
