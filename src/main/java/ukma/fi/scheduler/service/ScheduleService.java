package ukma.fi.scheduler.service;

import ukma.fi.scheduler.entities.Lesson;

import java.util.Map;
import java.util.Set;

public interface ScheduleService {

    Map<String, Set<Lesson>> findLessonsForStudent(String login);

    Map<String, Set<Lesson>> findLessonsForTeacher(String login);

}
