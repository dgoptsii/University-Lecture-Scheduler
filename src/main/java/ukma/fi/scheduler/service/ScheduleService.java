package ukma.fi.scheduler.service;

import ukma.fi.scheduler.entities.Lesson;

import java.util.Map;

public interface ScheduleService {

    Map<String, Lesson> findLessonsForStudent(String login);

    Map<String, Lesson> findLessonsForTeacher(String login);


}
