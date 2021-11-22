package ukma.fi.scheduler.service;

import ukma.fi.scheduler.entities.Lesson;

import java.util.List;

public interface LessonService {

    Lesson create(Long subject_id, Integer groupNumber, Integer lessonNumber, String dayOfWeek);

    boolean delete(Long id);

    Lesson edit(Lesson lesson);

    Lesson show(Long id);

    List<Lesson> findAllBySubject(Long subject_id);
}
