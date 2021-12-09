package ukma.fi.scheduler.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;
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
    private SubjectRepository subjectRepository;
    static final String SUBJECT1 = "BD";
    static final String SUBJECT2 = "OKA";
    static final String SUBJECT3 = "SP";

    static final String SPECIALTY1 = "IPZ";
    static final String SPECIALTY2 = "MATH";
    static final String SPECIALTY3 = "SPECIALTY";



    static List<Long>  subjectIDs = new ArrayList<>();

    @BeforeEach
    public void createBD() {
        Subject sub1  = new  Subject(SUBJECT1, 0, SPECIALTY1, 1);
        subjectIDs.add((Long) entityManager.persistAndGetId(sub1));
        Subject sub2  = new  Subject(SUBJECT2, 2, SPECIALTY2, 2);
        subjectIDs.add((Long) entityManager.persistAndGetId(sub2));
        Subject sub3  = new  Subject(SUBJECT3, 5, SPECIALTY3, 3);
        subjectIDs.add((Long) entityManager.persistAndGetId(sub3));

        User tech1 = new User();
        tech1.setName("N1");
        tech1.setSurname("S1");
        tech1.setStatus("STUDENT");

        User tech2 = new User();
        tech2.setName("N2");
        tech2.setSurname("S2");
        tech2.setStatus("STUDENT");

        User tech3 = new User();
        tech3.setName("N3");
        tech3.setSurname("S3");
        tech3.setStatus("TEACHER");

        User tech4 = new User();
        tech4.setName("N4");
        tech4.setSurname("S4");
        tech4.setStatus("TEACHER");
//        Subject subject, Integer dayOfWeek, Integer lessonNumber, User teacher, Integer groupNumber
//        Lesson les1 = new Lesson(sub1,1,1);
//        Lesson les2 = new Lesson(sub2,1,1);
//        Lesson les3 = new Lesson(sub3,1,1);
        entityManager.flush();
    }

    @Test
    public void shouldFindStudentByName() {
//        Subject found = subjectRepository.findSubjectByName(NAME1).get();
//        System.out.println(found);
//        assertThat(found.getName()).isEqualTo(NAME1);
    }

//
//    Optional<Lesson> findBySubjectAndGroupNumber(Subject subject, Integer groupNumber);
//    List<Lesson> findAllBySubjectAndGroupNumber(Subject subject, Integer groupNumber);
//    List<Lesson> findLessonsByGroupNumber(Integer groupNumber);
//    List<Lesson> findLessonsByGroupNumberNot(Integer groupNumber);
//    List<Lesson> findLessonsBySubject_Id(Long subject_id);
//    List<Lesson> findByTeacherLogin(String login);
//    void deleteById(Long id);
//    void deleteAllBySubject(Subject subject);
//    List<Lesson> findAll();

}
