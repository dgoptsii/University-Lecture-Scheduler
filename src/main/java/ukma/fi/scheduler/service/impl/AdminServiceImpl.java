package ukma.fi.scheduler.service.impl;
import lombok.extern.log4j.Log4j2;
import ukma.fi.scheduler.ServiceMarker;
import ukma.fi.scheduler.entities.*;
import ukma.fi.scheduler.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@ServiceMarker
@Service
@Log4j2
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
    public Lesson addLesson(Long subject_id, Integer groupNumber, Integer lessonNumber, String dayOfWeek) {
       return lessonService.create(subject_id,groupNumber,lessonNumber,dayOfWeek);
    }

    @Override
    public String deleteLesson(Long id) {
        if(lessonService.delete(id)){
            log.info("deleted lesson -> id:" + id);
            return "Deleting lesson id:"+id+" completed.";
        }
        return "ERROR";
    }

    @Override
    public void editLesson(Lesson lesson) {
        log.info("edit lesson -> id:" + lesson.getId());
        lessonService.edit(lesson);
    }

    @Override
    public Subject addSubject(String name, Long facultyId,String normative) {
        log.info("added subject -> name:" + name);
        return subjectService.create(name,facultyId,normative);
    }

    @Override
    public String deleteSubject(Long id) {
        if(subjectService.delete(id)){
            log.info("deleted subject -> id:" + id);
            return "Deleting subject id:"+id+" completed.";
        }
        return "ERROR";
    }

    @Override
    public void editSubject(Subject subject) {
        log.info("edit subject -> id:" + subject.getId());
        subjectService.edit(subject);
    }

    @Override
    public List<Subject> showFacultySubjects(Long faculty_id) {
        log.info("found by id -> faculty id:" + faculty_id);
        return subjectService.findByFaculty(faculty_id);
    }

    @Override
    public List<Lesson> showSubjectLessons(Long subject_id) {
        log.info("found by id -> subject id:" + subject_id);
        return lessonService.findAllBySubject(subject_id);
    }
}
