package ukma.fi.scheduler.service;

import ukma.fi.scheduler.controller.dto.LessonDTO;
import ukma.fi.scheduler.entities.Lesson;

import java.util.List;

public interface LessonService {

    Lesson create(LessonDTO dto) throws Exception;

    List<Lesson> findAll();

    List<Lesson> findAllBySubject_Id(Long id);

    Lesson findById(Long id) throws Exception;

    void edit(Long id, Lesson lesson) throws Exception;

    void delete(Long id) throws Exception;
}
