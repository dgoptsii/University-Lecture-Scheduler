package ukma.fi.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ukma.fi.scheduler.controller.dto.SubjectGroupDTO;
import ukma.fi.scheduler.controller.dto.SubjectGroupListDTO;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.service.ScheduleService;
import ukma.fi.scheduler.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private UserService userService;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/subject/groups")
    public ModelAndView addStudentGroup(Principal principal) {
        ModelAndView mav = new ModelAndView("student-add-group");
        List<Subject> normativeSubjects = userService.findNormativeSubjects(principal.getName());
        List<Subject> notNormativeSubjects = userService.findNonNormativeSubjects(principal.getName());
        User user = userService.findUserByLogin(principal.getName());
        Map<Subject, Integer> subGroupNum = user.getGroups();

        List<SubjectGroupDTO> normativeDto = new ArrayList<>();
        normativeSubjects.forEach(el -> normativeDto.add(new SubjectGroupDTO(el.getName(), el.getId(), subGroupNum.get(el), el.getMaxGroups(),true)));

        List<SubjectGroupDTO> nonNormativeDto = new ArrayList<>();
        notNormativeSubjects.forEach(el -> nonNormativeDto.add(new SubjectGroupDTO(el.getName(), el.getId(), subGroupNum.get(el), el.getMaxGroups(),false)));

        SubjectGroupListDTO fromData  = new SubjectGroupListDTO();
        fromData.addAllDto(normativeDto);
        fromData.addAllDto(nonNormativeDto);
        mav.addObject("form", normativeDto);
        return mav;
    }

    @GetMapping("/subject/groups")
    public ModelAndView addStudentGroup() {
        return new ModelAndView("student-add-group");
    }


    @GetMapping("/scheduler")
    public ModelAndView schedule(Principal principal) {
        ModelAndView mav = new ModelAndView("schedule");
        mav.addAllObjects(scheduleService.findLessonsForStudent(principal.getName()));
        return mav;
    }

}
