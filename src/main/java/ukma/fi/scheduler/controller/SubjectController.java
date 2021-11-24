package ukma.fi.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ukma.fi.scheduler.controller.dto.SubjectDTO;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.service.FacultyService;
import ukma.fi.scheduler.service.SubjectService;

import javax.validation.Valid;

@Controller
@RequestMapping("/subject")
@Validated
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private FacultyService facultyService;
//
//    @PostMapping("/add")
//    public void addSubject(@Valid @RequestBody SubjectDTO newSubject) {
//        subjectService.create(newSubject);
//    }

    @GetMapping("{id}")
    public ModelAndView getSubject(@Valid @PathVariable Long id) {
        ModelAndView mav = new ModelAndView("subject");
        Subject subject = subjectService.show(id);
        mav.addObject("subject", subject);
        mav.addObject("lessons", subject.getLessons());
        return mav;
    }

    @GetMapping("/showAll")
    public ModelAndView showAllSubjects() {
        ModelAndView mav = new ModelAndView("subjects");
        mav.addObject("subjects", subjectService.showAll());
        mav.addObject("faculties", facultyService.showAll());
        return mav;
    }

    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
    }
}
