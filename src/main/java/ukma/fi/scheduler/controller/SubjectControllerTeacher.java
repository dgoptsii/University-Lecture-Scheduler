package ukma.fi.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ukma.fi.scheduler.controller.dto.SubjectDTO;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.service.FacultyService;
import ukma.fi.scheduler.service.SubjectService;

import javax.validation.Valid;

@Controller
@RequestMapping("teacher/subject")
public class SubjectControllerTeacher {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private FacultyService facultyService;

    @GetMapping("{id}")
    public ModelAndView getSubject(@Valid @PathVariable Long id) {
        ModelAndView mav = new ModelAndView("subject_edit");
        Subject subject = subjectService.show(id);
        mav.addObject("subject", subject);
        mav.addObject("faculties", facultyService.showAll());
        mav.addObject("lessons", subject.getLessons());
        return mav;
    }

    @DeleteMapping("{id}")
    public RedirectView deleteSubject(@PathVariable Long id) {
        System.out.println("deleting"+id);
        return subjectService.delete(id) ? new RedirectView("/subject/showAll") : null;
    }

    @PutMapping("{id}")
    public RedirectView updateSubject(@ModelAttribute SubjectDTO newSubject, @PathVariable Long id) {
        newSubject.setId(id);
        subjectService.edit(newSubject);
        return new RedirectView("/subject/"+id);
    }
    ///////////////////////////////////

    @PostMapping("/add")
    public RedirectView addSubject(@ModelAttribute("subject") SubjectDTO newSubject, Model model) {
        System.out.println(newSubject);
        System.out.println(model);
        Subject subject = subjectService.create(newSubject);
        return new RedirectView("/subject/showAll");
    }

    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
    }
}
