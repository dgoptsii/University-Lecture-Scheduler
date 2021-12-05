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
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private UserService userService;

    @Autowired
    private LessonRepository lessonRepository;

    private void addLecturesToResult(Map<String, Lesson> result, List<Subject> subjects) {
        List<Lesson> normativeLectures = lessonRepository.findLessonsBySubjectInAndGroupNumber(subjects, 0);
        normativeLectures.forEach(lecture -> {
            result.put("l" + lecture.getDayOfWeek() + "_" + lecture.getLessonNumber(), lecture);
        });
    }

    private void addLessonsToResult(Map<String, Lesson> result, Map<Subject, Integer> studentLessons) {
        List<Lesson> lessons = lessonRepository.findLessonsBySubjectIn(studentLessons.keySet());
        studentLessons.forEach(((subject, group) -> {
            Optional<Lesson> optionalLesson = lessons.stream()
                    .filter(lesson ->
                            lesson.getSubject().equals(subject)
                                    && lesson.getGroupNumber().equals(group)).findAny();
            if (optionalLesson.isPresent()) {
                Lesson lesson = optionalLesson.get();
                result.put("l" + lesson.getDayOfWeek() + "_" + lesson.getLessonNumber(), lesson);
            }
        }));

    }

    @Override
    public Map<String, Lesson> findLessonsForStudent(String login) {
        User user = userService.findUserByLogin(login);
        Map<String, Lesson> res = new HashMap<>();

        Set<Subject> subjectsLectures = new HashSet<>(user.getGroups().keySet());
        subjectsLectures.addAll(userService.findNormativeSubjects(login));

        addLecturesToResult(res, new ArrayList<>(subjectsLectures));
        addLessonsToResult(res, user.getGroups());

        return res;
    }

    @Override
    public Map<String, Lesson> findLessonsForTeacher(String login) {
        Map<String, Lesson> res = new HashMap<>();
        List<Lesson> lessons = lessonRepository.findByTeacherLogin(login);
        lessons.forEach(lesson -> {
            res.put(lesson.getDayOfWeek() + "-" + lesson.getLessonNumber(), lesson);
        });
        return res;
    }

}
