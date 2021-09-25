package ukma.fi.scheduler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.db.entities.Lesson;
import ukma.fi.scheduler.db.entities.Subject;
import ukma.fi.scheduler.service.interf.*;

@Service
public class AdminService implements AdminServiceInterface {
    @Autowired
    private LessonServiceInterface lessonService;

    @Autowired
    private SubjectServiceInterface subjectService;

    @Autowired
    private ShiftServiceInterface shiftService;

    @Autowired
    private FacultyServiceInterface facultyService;

    @Override
    public Lesson createLesson(Subject subject, Integer lessonNumber, Integer dayOfWeek) {
        return null;
    }

    @Override
    public void deleteLesson(Lesson lesson) {

    }

    @Override
    public void editLesson(Lesson lesson) {

    }
}
