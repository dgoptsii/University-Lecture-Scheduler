package ukma.fi.scheduler.controller;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.repository.LessonRepository;
import ukma.fi.scheduler.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
public class DataJpaLessonTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LessonRepository lessonRepository;

    static final String SUBJECT1 = "BD";
    static final String SUBJECT2 = "OKA";
    static final String SUBJECT3 = "SP";

    static final String SPECIALTY1 = "IPZ";
    static final String SPECIALTY2 = "MATH";
    static final String SPECIALTY3 = "SPECIALTY";

    static final Subject sub1  = new  Subject(SUBJECT1, 0, SPECIALTY1, 1);
    static final Subject sub2  = new  Subject(SUBJECT2, 2, SPECIALTY2, 2);
    static final Subject sub3  = new  Subject(SUBJECT3, 5, SPECIALTY3, 3);


    static final String LOG1 = "log.a@ukma.edu.ua";
    static final String LOG2 = "log.b@ukma.edu.ua";
    static final String LOG3 = "log.c@ukma.edu.ua";
    static final String LOG4 = "log.d@ukma.edu.ua";

    static final String STATUS_STUDENT = "STUDENT";
    static final String STATUS_TEACHER = "STUDENT";

    static final String PASSWORD = "Password123";


    static List<Long>  userIDs = new ArrayList<>();

    static List<Long>  subjectIDs = new ArrayList<>();

    static List<Long>  lessonIDs = new ArrayList<>();

    @BeforeEach
    public void createBD() {

        subjectIDs.add((Long) entityManager.persistAndGetId(sub1));
        subjectIDs.add((Long) entityManager.persistAndGetId(sub2));
        subjectIDs.add((Long) entityManager.persistAndGetId(sub3));
        entityManager.flush();
        User tech1 = new User();
        tech1.setName("N1");
        tech1.setSurname("S1");
        tech1.setStatus(STATUS_STUDENT);
        tech1.setPassword(PASSWORD);
        tech1.setLogin(LOG1);

        User tech2 = new User();
        tech2.setName("N2");
        tech2.setSurname("S2");
        tech2.setStatus(STATUS_STUDENT);
        tech2.setPassword(PASSWORD);
        tech2.setLogin(LOG2);

        User tech3 = new User();
        tech3.setName("N3");
        tech3.setSurname("S3");
        tech3.setStatus(STATUS_TEACHER);
        tech3.setPassword(PASSWORD);
        tech3.setLogin(LOG3);

        User tech4 = new User();
        tech4.setName("N4");
        tech4.setSurname("S4");
        tech4.setStatus(STATUS_TEACHER);
        tech4.setPassword(PASSWORD);
        tech4.setLogin(LOG4);

        userIDs.add((Long) entityManager.persistAndGetId(tech1));
        userIDs.add((Long) entityManager.persistAndGetId(tech2));
        userIDs.add((Long) entityManager.persistAndGetId(tech3));
        userIDs.add((Long) entityManager.persistAndGetId(tech4));
        entityManager.flush();
//        0 groups max
        Lesson les1 = new Lesson(sub1,1,1, tech3,0);

//        2 groups max
        Lesson les2 = new Lesson(sub2,1,1, tech3,1) ;
        Lesson les3 = new Lesson(sub2,1,1, tech3,2) ;

//        5 groups max
        Lesson les4 = new Lesson(sub3,1,1, tech4,1);
        Lesson les5 = new Lesson(sub3,1,1, tech4,2);
        Lesson les6 = new Lesson(sub3,1,1, tech4,3);

        lessonIDs.add((Long) entityManager.persistAndGetId(les1));
        lessonIDs.add((Long) entityManager.persistAndGetId(les2));
        lessonIDs.add((Long) entityManager.persistAndGetId(les3));
        lessonIDs.add((Long) entityManager.persistAndGetId(les4));
        lessonIDs.add((Long) entityManager.persistAndGetId(les5));
        lessonIDs.add((Long) entityManager.persistAndGetId(les6));

        entityManager.flush();
    }


        @Test
    public void shouldFindLessonBySubjectAndGroupNumber(){
        List <Lesson> lessons = lessonRepository.findAllBySubjectAndGroupNumber(sub3,1);
        System.out.println(lessons);
    for (Lesson l :lessons){
        assertThat(l.getGroupNumber() == 1);
        assertThat(l.getSubject()).isEqualTo(sub3);
    }
        Optional<Lesson> lesson =  lessonRepository.findBySubjectAndGroupNumber(sub3,1);
        if (lesson.isPresent()){
            Lesson l = lesson.get();
            assertThat(l.getGroupNumber() == 1);
            assertThat(l.getSubject()).isEqualTo(sub3);
        }
    }

    @Test
    public void shouldFindLessonALl(){
        List<Lesson> list = lessonRepository.findAll();
        assertThat(list.size() == lessonIDs.size());
    }

    @Test
    public void shouldFindByGroupNumber(){
        List <Lesson> lessons = lessonRepository.findLessonsByGroupNumber(1);
        for (Lesson l :lessons){
            assertThat(l.getGroupNumber() == 1);
        }
    }

    @Test
    public void shouldFindByNotGroupNumber(){
        List <Lesson> lessons = lessonRepository.findLessonsByGroupNumberNot(1);
        for (Lesson l :lessons){
            assertThat(l.getGroupNumber() != 1);
        }
    }

    @Test
    public void shouldFindBySubjectId(){
        List <Lesson> lessons = lessonRepository.findLessonsBySubject_Id(sub3.getId());
        for (Lesson l :lessons){
            assertThat(l.getSubject().getId()).isEqualTo(sub3.getId());
        }
    }



//    List<Lesson> findLessonsBySubject_Id(Long subject_id);
//    List<Lesson> findByTeacherLogin(String login);
//    void deleteById(Long id);
//    void deleteAllBySubject(Subject subject);


}
