package ukma.fi.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ukma.fi.scheduler.controller.dto.UserDTO;
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
        if (user.getStatus().equals("STUDENT"))
            mav.setViewName("student-profile");
        else if (user.getStatus().equals("TEACHER"))
            mav.setViewName("teacher-profile");
        mav.addObject("user", userService.findUserByLogin(principal.getName()));
        return mav;
    }

    @GetMapping("/profile_edit")
    public ModelAndView profileEditPage(Principal principal) {
        ModelAndView mav = new ModelAndView();
        User user = userService.findUserByLogin(principal.getName());
        mav.setViewName("profile-edit");
        mav.addObject("user", userService.findUserByLogin(principal.getName()));
        return mav;
    }

    @PutMapping("/profile_edit")
    public RedirectView profilePage(@Valid @ModelAttribute UserDTO user, Principal principal) {
        authService.editUser(user, principal.getName());
        return new RedirectView("/profile");
    }

    @PostMapping("/registration")
    public RedirectView registration(@Valid @ModelAttribute User user) {
        authService.registration(user, "STUDENT");
        return new RedirectView("/login");
    }

    @GetMapping("/registration")
    public ModelAndView registerPage() {
        ModelAndView mav = new ModelAndView("student_registration");
        mav.addObject("specialties", SPECIALITIES);
        return mav;
    }


    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
    }

}
