package ukma.fi.scheduler.service;
import ukma.fi.scheduler.entities.*;

import java.util.List;

public interface AdminService {

    void addLesson(Subject subject, Integer lessonNumber, Integer dayOfWeek);

    void deleteLesson(Long id);

    void editLesson(Lesson lesson);


    Subject addSubject(Subject subject);

    void deleteSubject(Long id);

    void editSubject(Subject subject);


    List<Subject> showFacultySubjects(Long faculty_id);

    List<Lesson> showSubjectLessons(Long subject_id);
}
