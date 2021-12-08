package ukma.fi.scheduler.service;

import ukma.fi.scheduler.entities.*;

import java.util.List;

public interface LessonService {

    Lesson create(Lesson lesson);
    List<Lesson> findAll();
}
