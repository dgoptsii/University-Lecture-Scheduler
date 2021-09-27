package ukma.fi.scheduler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.entities.Faculty;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.service.*;
import ukma.fi.scheduler.service.FacultyService;
import ukma.fi.scheduler.service.LessonService;
import ukma.fi.scheduler.service.ShiftService;
import ukma.fi.scheduler.service.SubjectService;

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
    public void addLesson(Subject subject, Integer lessonNumber, Integer dayOfWeek) { }

    @Override
    public void deleteLesson(Lesson lesson) {

    }

    @Override
    public void editLesson(Lesson lesson) {

    }

    @Override
    public void addSubject(Subject subject) {

    }

    @Override
    public void deleteSubject(Subject subject) {

    }

    @Override
    public void editSubject(Subject subject) {

    }

    @Override
    public void showFacultySubjects(Faculty faculty) {

    }

    @Override
    public void showFacultyLessons(Subject subject) {

    }
}
