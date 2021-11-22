package ukma.fi.scheduler.service;

import ukma.fi.scheduler.controller.dto.SubjectDTO;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;

import java.util.List;

public interface AdminService {

    Lesson addLesson(Long subject_id, Integer groupNumber, Integer lessonNumber, String dayOfWeek);

    String deleteLesson(Long id);

    void editLesson(Lesson lesson);

    Subject addSubject(SubjectDTO subjectDTO);

    String deleteSubject(Long id);

    void editSubject(Subject subject);

    List<Subject> showFacultySubjects(Long faculty_id);

    List<Lesson> showSubjectLessons(Long subject_id);
}
