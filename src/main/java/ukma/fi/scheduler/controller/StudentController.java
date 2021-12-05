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
        mav.addObject("form", getSubjectGroupDTOS(principal.getName()));
        return mav;
    }
    @PostMapping("/subject/groups")
    public RedirectView addStudentGroup(@ModelAttribute SubjectGroupListDTO form, Principal principal) {
        SubjectGroupListDTO oldForm = getSubjectGroupDTOS(principal.getName());
        if(form.equals(oldForm)){
            return new RedirectView("/profile");
        }
        System.out.println("wow");
        User user = userService.findUserByLogin(principal.getName());
        Map<Subject, Integer> subGroupNum = user.getGroups();

        return new RedirectView("/profile");
    }


    private SubjectGroupListDTO getSubjectGroupDTOS(String login) {
        List<Subject> normativeSubjects = userService.findNormativeSubjects(login);
        List<Subject> notNormativeSubjects = userService.findNonNormativeSubjects(login);
        User user = userService.findUserByLogin(login);
        Map<Subject, Integer> subGroupNum = user.getGroups();

        List<SubjectGroupDTO> normativeDto = new ArrayList<>();
        normativeSubjects.forEach(el -> {
            Integer groupNum = (subGroupNum.get(el)==null)? 0:subGroupNum.get(el);
            normativeDto.add(new SubjectGroupDTO(el.getName(), el.getId(), groupNum, el.getMaxGroups(),true));
        });

        List<SubjectGroupDTO> nonNormativeDto = new ArrayList<>();
        notNormativeSubjects.forEach(el -> {
            Integer groupNum = (subGroupNum.get(el)==null)? 0:subGroupNum.get(el);
            nonNormativeDto.add(new SubjectGroupDTO(el.getName(), el.getId(), groupNum, el.getMaxGroups(),false));
        });

        SubjectGroupListDTO fromData  = new SubjectGroupListDTO();
        fromData.addAllDto(normativeDto);
        fromData.addAllDto(nonNormativeDto);
        return fromData;
    }




    @GetMapping("/scheduler")
    public ModelAndView schedule(Principal principal) {
        ModelAndView mav = new ModelAndView("schedule");
        mav.addAllObjects(scheduleService.findLessonsForStudent(principal.getName()));
        return mav;
    }

}
