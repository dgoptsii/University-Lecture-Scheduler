package ukma.fi.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.service.AuthService;


@Controller
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
