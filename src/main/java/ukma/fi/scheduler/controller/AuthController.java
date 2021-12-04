package ukma.fi.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.repository.UserRepository;
import ukma.fi.scheduler.service.AuthService;
import ukma.fi.scheduler.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;


@Controller
public class AuthController {

    private static final ArrayList<String> SPECIALITIES = new ArrayList<>();

    static {
        SPECIALITIES.add("IPZ");
        SPECIALITIES.add("Computer Science");
        SPECIALITIES.add("Math");
    }

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ModelAndView profilePage(Principal principal) {
        ModelAndView mav = new ModelAndView();

        User user = userService.findUserByLogin(principal.getName());
        if(user.getStatus().equals("STUDENT"))
            mav.setViewName("student-profile");
        else if(user.getStatus().equals("TEACHER"))
            mav.setViewName("teacher-profile");
        mav.addObject("user", userService.findUserByLogin(principal.getName()));
        return mav;
    }

    @PostMapping("/registration")
    public void registration(@Valid @ModelAttribute User user) {
        authService.registration(user, "STUDENT");
        new RedirectView("/profile");
    }

    @GetMapping("/registration")
    public ModelAndView registerPage() {
        ModelAndView mav = new ModelAndView("student_registration");
        mav.addObject("specialties", SPECIALITIES);
        return mav;
    }

}
