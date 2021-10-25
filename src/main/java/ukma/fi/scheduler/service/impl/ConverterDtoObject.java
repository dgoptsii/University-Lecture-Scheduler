package ukma.fi.scheduler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.controller.dto.SubjectDTO;
import ukma.fi.scheduler.controller.dto.UserDTO;
import ukma.fi.scheduler.entities.Faculty;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.service.FacultyService;
import ukma.fi.scheduler.service.SubjectService;

@Service
public class ConverterDtoObject {
    @Autowired
    private static FacultyService facultyService;
    @Autowired
    private static SubjectService subjectService;

    public static User createUserFromDTO(UserDTO user){
        User result = new User();
        result.setLogin(user.getLogin());
        result.setPassword(user.getPassword());
        result.setStatus(user.getStatus());
        Faculty faculty = facultyService.show(user.getFacultyId());
        result.setFaculty(faculty);
        return result;
    }

    public static Subject createSubjectFromDTO(SubjectDTO subject){
        Subject result = new Subject();
        result.setName(subject.getName());
        result.setNormative(subject.getNormative());
        Faculty faculty = facultyService.show(subject.getFacultyId());
        result.setFaculty(faculty);
        return result;
    }
}
