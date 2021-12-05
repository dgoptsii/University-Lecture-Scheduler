package ukma.fi.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.service.AuthService;
import ukma.fi.scheduler.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private UserService userService;

    @GetMapping("/subject/add")
    public ModelAndView addStudentSubject(Principal principal){
        ModelAndView mav = new ModelAndView("student-add-subject");
        List<Subject> addSubjects = userService.findNonNormativeFreeSubjects(principal.getName());
        List<Subject> userNonNormative = userService.findNonNormativeSubjects(principal.getName());
        mav.addObject("addSubjects", addSubjects);
        mav.addObject("userSubjects", userNonNormative);
        return mav;
    }


    @GetMapping("/subject/groups")
    public ModelAndView addStudentGroup(){
        return new ModelAndView("student-add-group");
    }

}
