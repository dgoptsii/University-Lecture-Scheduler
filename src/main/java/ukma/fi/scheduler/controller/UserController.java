package ukma.fi.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ukma.fi.scheduler.controller.dto.UserDTO;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.exceptionHandlers.exceptions.LessonNotFoundException;
import ukma.fi.scheduler.service.AuthService;
import ukma.fi.scheduler.service.LessonService;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private AuthService authService;

    @PostMapping("/add")
    public void register(@Valid @RequestBody UserDTO user) {
        authService.registration(User.createFromDTO(user));
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return authService.getUserInfo(id);
    }
}
