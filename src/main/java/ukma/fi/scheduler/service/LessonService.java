package ukma.fi.scheduler.service;

import ukma.fi.scheduler.controller.dto.LessonDTO;
import ukma.fi.scheduler.entities.*;

import java.util.List;

public interface LessonService {

    Lesson create(LessonDTO dto);
    List<Lesson> findAll();
    List<Lesson> findAllBySubject_Id(Long id);
    Lesson findById(Long id);
    void edit(Long id, Lesson lesson);
    void delete(Long id);
}
