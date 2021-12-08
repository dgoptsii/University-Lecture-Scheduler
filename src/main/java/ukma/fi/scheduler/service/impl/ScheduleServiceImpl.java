package ukma.fi.scheduler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.repository.LessonRepository;
import ukma.fi.scheduler.service.ScheduleService;
import ukma.fi.scheduler.service.UserService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private UserService userService;

    @Autowired
    private LessonRepository lessonRepository;

    private void addLecturesToResult(Map<String, Set<Lesson>> result, Set<Subject> subjects) {
        List<Lesson> normativeLectures = lessonRepository.findLessonsByGroupNumber(0);
        normativeLectures = normativeLectures.stream()
                .filter(x -> subjects.contains(x.getSubject()))
                .collect(Collectors.toList());
        convertToSchedulerDto(result, normativeLectures);
    }

    private void addLessonsToResult(Map<String, Set<Lesson>> result, Map<Subject, Integer> studentLessons) {
        List<Lesson> lessons = lessonRepository.findLessonsByGroupNumberNot(0);

        lessons = lessons.stream()
                .filter(x ->
                        studentLessons.containsKey(x.getSubject()) &&
                        studentLessons.get(x.getSubject()).equals(x.getGroupNumber()))
                .collect(Collectors.toList());

        convertToSchedulerDto(result, lessons);
    }

    private void convertToSchedulerDto(Map<String, Set<Lesson>> result, List<Lesson> lessons) {
        lessons.forEach(x -> {
            String key = "l" + x.getDayOfWeek() + "_" + x.getLessonNumber();
            if (result.containsKey(key)) {
                result.get(key).add(x);
            } else {
                Set<Lesson> newList = new HashSet<>();
                newList.add(x);
                result.put(key, newList);
            }
        });
    }

    @Override
    public Map<String, Set<Lesson>> findLessonsForStudent(String login) {
        User user = userService.findUserByLogin(login);
        Map<String, Set<Lesson>> res = new HashMap<>();

        Set<Subject> subjectsLectures = new HashSet<>(user.getGroups().keySet());
        subjectsLectures.addAll(userService.findNormativeSubjects(login));

        addLecturesToResult(res, subjectsLectures);
        addLessonsToResult(res, user.getGroups());

        return res;
    }

    @Override
    public Map<String, Set<Lesson>> findLessonsForTeacher(String login) {
        Map<String, Set<Lesson>> result = new HashMap<>();
        List<Lesson> lessons = lessonRepository.findByTeacherLogin(login);
        convertToSchedulerDto(result, lessons);
        return result;
    }

}
