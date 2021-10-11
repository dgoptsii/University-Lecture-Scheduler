package ukma.fi.scheduler.service;
import ukma.fi.scheduler.entities.*;

import java.util.List;

public interface AdminService {

    Lesson addLesson(Long subject_id, Integer lessonNumber, Integer dayOfWeek);

    String deleteLesson(Long id);

    void editLesson(Lesson lesson);

    Subject addSubject(Subject subject);

    String deleteSubject(Long id);

    void editSubject(Subject subject);


    List<Subject> showFacultySubjects(Long faculty_id);

    List<Lesson> showSubjectLessons(Long subject_id);
}
