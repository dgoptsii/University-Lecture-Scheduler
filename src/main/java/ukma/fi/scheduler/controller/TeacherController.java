package ukma.fi.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ukma.fi.scheduler.controller.dto.SubjectLectureDTO;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
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
        return new RedirectView("teacher/subject/add");
    }

    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
    }

}
