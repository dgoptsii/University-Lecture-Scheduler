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

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private UserService userService;

    @Autowired
    private LessonRepository lessonRepository;

    private void addLecturesToResult(Map<String, Set<Lesson>> result, List<Subject> subjects) {
        List<Lesson> normativeLectures = lessonRepository.findLessonsBySubjectInAndGroupNumber(subjects, 0);
        normativeLectures.forEach(lecture -> {
            String key = "l" + lecture.getDayOfWeek() + "_" + lecture.getLessonNumber();
            if (result.containsKey(key)) {
                result.get(key).add(lecture);
            } else {
                Set<Lesson> newList = new HashSet<>();
                newList.add(lecture);
                result.put(key, newList);
            }
        });
    }

    private void addLessonsToResult(Map<String, Set<Lesson>> result, Map<Subject, Integer> studentLessons) {
        List<Lesson> lessons = lessonRepository.findLessonsBySubjectIn(studentLessons.keySet());
        studentLessons.forEach(((subject, group) -> {
            Optional<Lesson> optionalLesson = lessons.stream()
                    .filter(lesson ->
                            lesson.getSubject().equals(subject)
                                    && lesson.getGroupNumber().equals(group)).findAny();
            if (optionalLesson.isPresent()) {
                Lesson lesson = optionalLesson.get();
                String key = "l" + lesson.getDayOfWeek() + "_" + lesson.getLessonNumber();
                if (result.containsKey(key)) {
                    result.get(key).add(lesson);
                } else {
                    Set<Lesson> newList = new HashSet<>();
                    newList.add(lesson);
                    result.put(key, newList);
                }
            }
        }));

    }

    @Override
    public Map<String, Set<Lesson>> findLessonsForStudent(String login) {
        User user = userService.findUserByLogin(login);
        Map<String, Set<Lesson>> res = new HashMap<>();

        Set<Subject> subjectsLectures = new HashSet<>(user.getGroups().keySet());
        subjectsLectures.addAll(userService.findNormativeSubjects(login));

        addLecturesToResult(res, new ArrayList<>(subjectsLectures));
        addLessonsToResult(res, user.getGroups());

        return res;
    }

    @Override
    public Map<String, Set<Lesson>> findLessonsForTeacher(String login) {
        Map<String, Set<Lesson>> result = new HashMap<>();
        List<Lesson> lessons = lessonRepository.findByTeacherLogin(login);
        lessons.forEach(lesson -> {
            String key = "l" + lesson.getDayOfWeek() + "_" + lesson.getLessonNumber();
            if (result.containsKey(key)) {
                result.get(key).add(lesson);
            } else {
                Set<Lesson> newList = new HashSet<>();
                newList.add(lesson);
                result.put(key, newList);
            }
        });
        return result;
    }

}
