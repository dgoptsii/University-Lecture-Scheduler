package ukma.fi.scheduler.service.impl;
import com.sun.media.sound.InvalidDataException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.entities.*;
import ukma.fi.scheduler.repository.*;
import ukma.fi.scheduler.service.*;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public Lesson create(Long subject_id, Integer groupNumber, Integer lessonNumber, String dayOfWeek) {
        Lesson lesson = new Lesson(subject_id,groupNumber,lessonNumber,dayOfWeek);
        checkParams(lesson);
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
        checkParams(lesson);
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
        return lessonRepository.findById(id).get();
    }

    @Override
    public List<Lesson> findAllBySubject(Long subject_id) {
        if(!lessonRepository.findAllBySubject(subject_id).isPresent()) {
            try {
                throw new InvalidDataException("Lesson id is incorrect");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        return lessonRepository.findAllBySubject(subject_id);
    }

    private void checkParams(Lesson lesson) {

    }
}
