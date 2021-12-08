package ukma.fi.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ukma.fi.scheduler.controller.dto.LessonDTO;
import ukma.fi.scheduler.controller.dto.SubjectLectureDTO;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.service.LessonService;
import ukma.fi.scheduler.service.SubjectService;
import ukma.fi.scheduler.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("teacher")
public class TeacherController {

    private static final ArrayList<String> SPECIALITIES = new ArrayList<>();

    static {
        SPECIALITIES.add("IPZ");
        SPECIALITIES.add("Computer Science");
        SPECIALITIES.add("Math");
    }

    @Autowired
    private UserService userService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private LessonService lessonService;

    @GetMapping("/subject/add")
    public ModelAndView addSubject() {
        ModelAndView mav = new ModelAndView("teacher_add_subject");
        mav.addObject("specialties", SPECIALITIES);
        mav.addObject("teachers", userService.findByRole("TEACHER"));
        return mav;
    }

    @PostMapping("/subject/add")
    public RedirectView addSubject(@ModelAttribute("subject") @Valid SubjectLectureDTO dto) {
        subjectService.create(dto);
        return new RedirectView("/teacher/subject/add");
    }

    @GetMapping("/lesson/add")
    public ModelAndView addLesson() {
        ModelAndView mav = new ModelAndView("teacher_add_lesson");
        mav.addObject("specialties", SPECIALITIES);
        mav.addObject("subjects", subjectService.findAll());
        mav.addObject("teachers", userService.findByRole("TEACHER"));
        return mav;
    }

    @GetMapping("/subject/all")
    public ModelAndView allSubjects() {
        ModelAndView mav = new ModelAndView("teacher-all-subjects");
        mav.addObject("subjects", subjectService.findAll());
        mav.addObject("teachers", userService.findByRole("TEACHER"));
        return mav;
    }

    @GetMapping("/subject/{id}")
    public ModelAndView getSubject(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("teacher_edit_subject");
        Subject subject = subjectService.findSubjectById(id);
        List<Lesson> lessons = lessonService.findAllBySubject_Id(id);
        mav.addObject("specialties", SPECIALITIES);
        mav.addObject("teachers", userService.findByRole("TEACHER"));
        mav.addObject("lessons", lessons);
        mav.addObject("subject",subject);
        return mav;
    }

    @PutMapping("/subject/{id}")
    public RedirectView updateSubject(@PathVariable Long id,@Valid Subject subject){
        subjectService.edit(id,subject);
        return new RedirectView("/teacher/subject/"+id);
    }

    @DeleteMapping("/subject/{id}")
    public RedirectView deleteSubject(@PathVariable Long id){
        subjectService.deleteSubject(id);
        return new RedirectView("/teacher/subject/all");
    }

    @GetMapping("/lesson/{id}")
    public ModelAndView getLesson(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("teacher_edit_lesson");
        Lesson lesson = lessonService.findById(id);
        mav.addObject("lesson", lesson);
        mav.addObject("specialties", SPECIALITIES);
        mav.addObject("subjects", subjectService.findAll());
        mav.addObject("teachers", userService.findByRole("TEACHER"));
        return mav;
    }

    @DeleteMapping("/lesson/{id}")
    public RedirectView deleteLesson(@PathVariable Long id){
        Lesson lesson = lessonService.findById(id);
        lessonService.delete(id);
        return new RedirectView("/teacher/subject/"+lesson.getSubject().getId());
    }


    @PostMapping("/lesson/add")
    public RedirectView addLesson(@ModelAttribute("lesson") @Valid LessonDTO dto) {
        lessonService.create(dto);
        return new RedirectView("/teacher/lesson/add");
    }

    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
    }

}
