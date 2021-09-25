package ukma.fi.scheduler.service.impl.notuseful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.db.entities.Faculty;
import ukma.fi.scheduler.db.entities.Lesson;
import ukma.fi.scheduler.db.entities.Subject;
import ukma.fi.scheduler.db.entities.User;

@Service
public class EntityCreationServiceImpl {

    @Autowired
    private DataValidationServiceImpl dataValidationService;

    public User createUser(String login, String password, boolean isAdmin) throws Exception {
        User user = new User();
        //TODO add login and regexes
        if (dataValidationService.isValid(login, "")) {
            if (dataValidationService.isValid(password, "")) {
                user.setLogin(login);
                user.setPassword(password);
                user.setAdmin(isAdmin);
                return user;
            } else {
                throw new Exception();
            }
        }
        throw new Exception();
    }

    public Faculty createFaculty(String name) throws Exception {
        Faculty faculty = new Faculty();
        //TODO add regex
        if (dataValidationService.isValid(name, "")) {
                faculty.setName(name);
                return faculty;
        }
        throw new Exception();
    }

    public Lesson createLesson(String name, Faculty faculty) throws Exception {
        Lesson lesson = new Lesson ();
        //TODO add regex
        if (dataValidationService.isValid(name, "")) {

            return lesson;
        }
        throw new Exception();
    }

    public Subject createSubject(String name) throws Exception {
        Subject subject = new Subject ();
        //TODO add regex
        if (dataValidationService.isValid(name, "")) {
            subject.setName(name);
            return subject;
        }
        throw new Exception();
    }

//    public Shift createShift(Integer weekNumber, String dayOfWeek) throws Exception {
//        Shift shift = new Shift ();
//        //TODO add regex
//        if (dataValidationService.isValid(name, "")) {
//            shift.setName(name);
//            return shift;
//        }
//        throw new Exception();
//    }



}
