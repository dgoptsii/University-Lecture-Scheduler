package ukma.fi.scheduler.service;
import ukma.fi.scheduler.entities.*;

public interface AdminService {

    void addLesson(Subject subject, Integer lessonNumber, Integer dayOfWeek);

    void deleteLesson(Lesson lesson);

    void editLesson(Lesson lesson);


    void addSubject(Subject subject);

    void deleteSubject(Subject subject);

    void editSubject(Subject subject);


    void showFacultySubjects(Faculty faculty);

    void showFacultyLessons(Subject subject);
}
