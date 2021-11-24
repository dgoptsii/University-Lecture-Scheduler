package ukma.fi.scheduler.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.service.LessonService;

import javax.validation.Valid;

@Controller
@RequestMapping("/lesson")
@Validated
@Log4j2
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping("/{id}")
    public String getLesson(@Valid @PathVariable Long id) {
        return  lessonService.show(id).toString();
    }

    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
    }

}
