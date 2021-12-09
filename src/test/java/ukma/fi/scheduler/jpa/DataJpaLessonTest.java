package ukma.fi.scheduler.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.repository.LessonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
public class DataJpaLessonTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LessonRepository lessonRepository;

    final String SUBJECT1 = "BD";
    final String SUBJECT2 = "OKA";
    final String SUBJECT3 = "SP";

    final String SPECIALTY1 = "IPZ";
    final String SPECIALTY2 = "MATH";
    final String SPECIALTY3 = "SPECIALTY";

    final Subject sub1 = new Subject(SUBJECT1, 0, SPECIALTY1, 1);
    final Subject sub2 = new Subject(SUBJECT2, 2, SPECIALTY2, 2);
    final Subject sub3 = new Subject(SUBJECT3, 5, SPECIALTY3, 3);


    final String LOG1 = "log.a@ukma.edu.ua";
    final String LOG2 = "log.b@ukma.edu.ua";
    final String LOG3 = "log.c@ukma.edu.ua";
    final String LOG4 = "log.d@ukma.edu.ua";

    final String STATUS_STUDENT = "STUDENT";
    final String STATUS_TEACHER = "STUDENT";

    final String PASSWORD = "Password123";
    User tech1 = new User(LOG1, "N1", "S1", STATUS_STUDENT, PASSWORD);
    User tech2 = new User(LOG2, "N2", "S2", STATUS_STUDENT, PASSWORD);
    User tech3 = new User(LOG3, "N3", "S3", STATUS_TEACHER, PASSWORD);
    User tech4 = new User(LOG4, "N4", "S4", STATUS_TEACHER, PASSWORD);


    List<Long> userIDs = new ArrayList<>();

    List<Long> subjectIDs = new ArrayList<>();

    List<Long> lessonIDs = new ArrayList<>();

    @BeforeEach
    public void createBD() {

        subjectIDs.add((Long) entityManager.persistAndGetId(sub1));
        subjectIDs.add((Long) entityManager.persistAndGetId(sub2));
        subjectIDs.add((Long) entityManager.persistAndGetId(sub3));
        entityManager.flush();

        userIDs.add((Long) entityManager.persistAndGetId(tech1));
        userIDs.add((Long) entityManager.persistAndGetId(tech2));
        userIDs.add((Long) entityManager.persistAndGetId(tech3));
        userIDs.add((Long) entityManager.persistAndGetId(tech4));
        entityManager.flush();
//        0 groups max
        Lesson les1 = new Lesson(sub1, 1, 1, tech3, 0);

//        2 groups max
        Lesson les2 = new Lesson(sub2, 1, 1, tech3, 1);
        Lesson les3 = new Lesson(sub2, 1, 1, tech3, 2);

//        5 groups max
        Lesson les4 = new Lesson(sub3, 1, 1, tech4, 1);
        Lesson les5 = new Lesson(sub3, 1, 1, tech4, 2);
        Lesson les6 = new Lesson(sub3, 1, 1, tech4, 3);

        lessonIDs.add((Long) entityManager.persistAndGetId(les1));
        lessonIDs.add((Long) entityManager.persistAndGetId(les2));
        lessonIDs.add((Long) entityManager.persistAndGetId(les3));
        lessonIDs.add((Long) entityManager.persistAndGetId(les4));
        lessonIDs.add((Long) entityManager.persistAndGetId(les5));
        lessonIDs.add((Long) entityManager.persistAndGetId(les6));

        entityManager.flush();
    }


    @Test
    public void shouldFindLessonBySubjectAndGroupNumber() {
        List<Lesson> lessons = lessonRepository.findAllBySubjectIdAndGroupNumber(sub3.getId(), 1);
        System.out.println(lessons);
        for (Lesson l : lessons) {
            Assertions.assertEquals(l.getGroupNumber(), 1);
            Assertions.assertEquals(l.getSubject(), sub3);
        }
        Optional<Lesson> lesson = lessonRepository.findBySubjectIdAndGroupNumber(sub3.getId(), 1);
        if (lesson.isPresent()) {
            Lesson l = lesson.get();
            Assertions.assertEquals(l.getGroupNumber(), 1);
            Assertions.assertEquals(l.getSubject(), sub3);
        }
    }

    @Test
    public void shouldFindLessonALl() {
        List<Lesson> list = lessonRepository.findAll();
        Assertions.assertEquals(list.size(), lessonIDs.size());
    }

    @Test
    public void shouldFindByGroupNumber() {
        List<Lesson> lessons = lessonRepository.findLessonsByGroupNumber(1);
        for (Lesson l : lessons) {
            Assertions.assertEquals(l.getGroupNumber(), 1);
        }
    }

    @Test
    public void shouldFindByNotGroupNumber() {
        List<Lesson> lessons = lessonRepository.findLessonsByGroupNumberNot(1);
        for (Lesson l : lessons) {
            Assertions.assertNotEquals(l.getGroupNumber(), 1);
        }
    }

    @Test
    public void shouldFindBySubjectId() {
        List<Lesson> lessons = lessonRepository.findLessonsBySubject_Id(sub3.getId());
        for (Lesson l : lessons) {
            Assertions.assertEquals(l.getSubject().getId(), sub3.getId());
        }
    }

    @Test
    public void shouldFindByTeacherLogin() {
        List<Lesson> lessons = lessonRepository.findByTeacherLogin(tech4.getLogin());
        for (Lesson l : lessons) {
            Assertions.assertEquals(l.getTeacher().getLogin(), tech4.getLogin());
        }
    }


    @Test
    public void shouldDeleteLessonById() {
        Long id = lessonIDs.get(0);
        lessonRepository.deleteById(id);
        Optional<Lesson> lesson = lessonRepository.findById(id);
        Assertions.assertFalse(lesson.isPresent());
    }


}
