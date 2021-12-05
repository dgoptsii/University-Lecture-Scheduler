package ukma.fi.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ukma.fi.scheduler.controller.dto.SubjectGroupDTO;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.service.AuthService;
import ukma.fi.scheduler.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

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
    public ModelAndView addStudentGroup(Principal principal){
        ModelAndView mav = new ModelAndView("student-add-group");
        List<Subject> normativeSubjects = userService.findNormativeSubjects(principal.getName());
        List<Subject> notNormativeSubjects = userService.findNonNormativeSubjects(principal.getName());
        User user = userService.findUserByLogin(principal.getName());
        Map<Subject, Integer> subGroupNum = user.getGroups();
        List<SubjectGroupDTO> normativeDto = new ArrayList<>();
        normativeSubjects.forEach( el ->  {
            normativeDto.add(new SubjectGroupDTO(el.getName(), el.getId(), subGroupNum.get(el), el.getMaxGroups()));
        });
        mav.addObject("normative", normativeDto);
        mav.addObject("notNormative", notNormativeSubjects);
        return mav;
    }

}
