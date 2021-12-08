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
    public RedirectView addSubject(@ModelAttribute("subject") SubjectLectureDTO dto) {
        Subject newSubject = new Subject(dto.getName(), dto.getMaxGroups(), dto.getSpecialty(), dto.getYear());
        Lesson newLesson = new Lesson(newSubject, dto.getDayOfWeek(), dto.getLessonNumber(), dto.getTeacher(),0);
        subjectService.create(newSubject);
        lessonService.create(newLesson);
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
        List<Lesson> lessons = lessonService.findAll();
        mav.addObject("specialties", SPECIALITIES);
        mav.addObject("teachers", userService.findByRole("TEACHER"));
        mav.addObject("lessons", lessons);
        //TODO find lessons by subject id
        mav.addObject("subject",subject);
        return mav;
    }

    @DeleteMapping("/subject/{id}")
    public RedirectView deleteSubject(@PathVariable Long id){
        //TODO delete subject
        return new RedirectView("/teacher/subject/all");
    }

    @GetMapping("/lesson/{id}")
    public ModelAndView getLesson(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("teacher_edit_lesson");
//        Lesson lesson = lessonService.findById();
        //TODO find lesson by id
        mav.addObject("specialties", SPECIALITIES);
        mav.addObject("subjects", subjectService.findAll());
        mav.addObject("teachers", userService.findByRole("TEACHER"));
        return mav;
    }

    @DeleteMapping("/lesson/{id}")
    public RedirectView deleteLesson(@PathVariable Long id){
        //TODO delete subject, redirect back to lesson page EBU KAK
        return new RedirectView("/teacher/subject/all");
    }


    @PostMapping("/lesson/add")
    public RedirectView addLesson(@ModelAttribute("lesson") LessonDTO dto) {
        Lesson newLesson = new Lesson(dto.getSubject(), dto.getDayOfWeek(), dto.getLessonNumber(), dto.getTeacher(),dto.getGroupNumber());
        lessonService.create(newLesson);
        return new RedirectView("/teacher/lesson/add");
    }

    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
    }

}
