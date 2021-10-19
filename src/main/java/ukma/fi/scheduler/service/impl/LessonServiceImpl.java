package ukma.fi.scheduler.service.impl;
import com.sun.media.sound.InvalidDataException;
import javassist.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.ServiceMarker;
import ukma.fi.scheduler.entities.*;
import ukma.fi.scheduler.repository.*;
import ukma.fi.scheduler.service.*;

import java.util.List;

@ServiceMarker
@Service
@Log4j2
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Lesson create(Long subject_id, Integer groupNumber, Integer lessonNumber, String dayOfWeek) {

        if(!subjectRepository.findById(subject_id).isPresent()){
            try {
                throw new InvalidDataException("Subject id is incorrect");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        if(groupNumber<=0 || dayOfWeek == null || lessonNumber<=0){
            try {
                throw new InvalidDataException("Invalid input data.");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        Lesson lesson = new Lesson(subjectRepository.findById(subject_id).get(),groupNumber,lessonNumber,dayOfWeek);
        log.info("created lesson -> subject id:" + subject_id);
        return lessonRepository.save(lesson);
    }

    @Override
    public boolean delete(Long id) {
        if (lessonRepository.findById(id).isPresent()) {
            lessonRepository.deleteById(id);
        } else {
            try {
                throw new NotFoundException("Lesson not found.");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            return false;
        }
        log.info("delete lesson -> id:" + id);
        return !lessonRepository.findById(id).isPresent();
    }

    @Override
    public Lesson edit(Lesson lesson) {
        if (!lessonRepository.findById(lesson.getId()).isPresent()) {
            try {
                throw new NotFoundException("Lesson not found.");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        if(lesson.getGroupNumber()<=0 || lesson.getDayOfWeek() == null || lesson.getGroupNumber()<=0){
            try {
                throw new InvalidDataException("Invalid input data.");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        log.info("edit lesson -> id:" + lesson.getId());
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson show(Long id) {
        if (!lessonRepository.findById(id).isPresent()) {
            try {
                throw new NotFoundException("Lesson not found.");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        log.info("show lesson -> id:" + id);
        return lessonRepository.findById(id).get();
    }

    @Override
    public List<Lesson> findAllBySubject(Long subject_id) {
        if(!subjectRepository.findById(subject_id).isPresent()){
            try {
                throw new InvalidDataException("Subject id is incorrect");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        log.info("found by id -> subject id:" + subject_id);
        return lessonRepository.findAllBySubject(subject_id);
    }

}
