package ukma.fi.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ukma.fi.scheduler.controller.dto.SubjectDTO;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.exceptionHandlers.exceptions.FacultyNotFoundException;
import ukma.fi.scheduler.exceptionHandlers.exceptions.SubjectNotFoundException;
import ukma.fi.scheduler.service.FacultyService;
import ukma.fi.scheduler.service.LessonService;
import ukma.fi.scheduler.service.SubjectService;

import javax.validation.Valid;

@RestController
@RequestMapping("teacher/subject")
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

    @GetMapping("/{id}")
    public String getSubject(@Valid @PathVariable Long id) {
        return subjectService.show(id).toString();
    }

    @DeleteMapping("/{id}")
    public String deleteSubject(@Valid @PathVariable Long id) {
        if (subjectService.delete(id))
            return "Delete subject with id = "+id;
        else
            return null;
    }
    @PutMapping("/{id}")
    public void updateSubject(@Valid @RequestBody Subject newSubject, @PathVariable Long id) {
        newSubject.setId(id);
        subjectService.edit(newSubject);
    }
    ///////////////////////////////////

    @GetMapping("/add_new_subject")
    public ModelAndView showAddSubjectsPage() {
        ModelAndView mav = new ModelAndView("add_subject");
        mav.addObject("faculties", facultyService.showAll());
        SubjectDTO subject = new SubjectDTO();
        mav.addObject("subject", subject);
        return mav;
    }

    @PostMapping("/add")
    public String addSubject( @Valid @ModelAttribute("subject") SubjectDTO newSubject) {
        System.out.println(newSubject);
        subjectService.create(newSubject);
        return "redirect:/subjects";
    }


    @GetMapping("/showAll")
    public ModelAndView showAllSubjects() {
        ModelAndView mav = new ModelAndView("subjects");
        mav.addObject("subjects", subjectService.showAll());
        return mav;
    }
}
