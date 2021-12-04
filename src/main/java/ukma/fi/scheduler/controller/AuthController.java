package ukma.fi.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.repository.SubjectRepository;
import ukma.fi.scheduler.repository.UserRepository;
import ukma.fi.scheduler.service.AuthService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/registration")
    public void registration(@ModelAttribute User user) {
        authService.registration(user);
    }

    @GetMapping("/registration")
    public ModelAndView registerPage(){
        ModelAndView mav = new ModelAndView("student_registration");
        List<String> specialities = new ArrayList<>();
        specialities.add("Spec1");
        specialities.add("Spec2");
        mav.addObject("specialties", specialities);
        return mav;
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
