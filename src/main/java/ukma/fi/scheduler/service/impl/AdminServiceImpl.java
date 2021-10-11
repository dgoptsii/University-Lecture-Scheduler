package ukma.fi.scheduler.service.impl;
import ukma.fi.scheduler.entities.*;
import ukma.fi.scheduler.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private LessonService lessonService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private FacultyService facultyService;

    @Override
    public Lesson addLesson(Long subject_id, Integer lessonNumber, Integer dayOfWeek) {
        return null;
    }

    @Override
    public String deleteLesson(Long id) {
        return null;
    }

    @Override
    public void editLesson(Lesson lesson) {
    }

    @Override
    public Subject addSubject(Subject subject) {
        return subjectService.create(subject);
    }

    @Override
    public String deleteSubject(Long id) {
        if(subjectService.delete(id)){
            return "Deleting subject id:"+id+" completed.";
        }
        return "ERROR";
    }

    @Override
    public void editSubject(Subject subject) {
        subjectService.edit(subject);
    }

    @Override
    public List<Subject> showFacultySubjects(Long faculty_id) {
        return subjectService.findByFaculty(faculty_id);
    }

    @Override
    public List<Lesson> showSubjectLessons(Long subject_id) {
        return null;
    }
}
