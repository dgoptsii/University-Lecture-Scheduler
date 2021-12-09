package ukma.fi.scheduler.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.repository.SubjectRepository;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
public class DataJpaSubjectTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SubjectRepository subjectRepository;
    final String NAME1 = "BD";
    final String NAME2 = "OKA";
    final String NAME3 = "SP";

    final String SPECIALTY1 = "IPZ";
    final String SPECIALTY2 = "MATH";
    final String SPECIALTY3 = "SPECIALTY";

    List<Long> subjectIDs = new ArrayList<>();

    @BeforeEach
    public void createBD() {
        Subject sub1 = new Subject(NAME1, 1, SPECIALTY1, 1);
        subjectIDs.add((Long) entityManager.persistAndGetId(sub1));
        Subject sub2 = new Subject(NAME2, 2, SPECIALTY2, 2);
        subjectIDs.add((Long) entityManager.persistAndGetId(sub2));
        Subject sub3 = new Subject(NAME3, 3, SPECIALTY3, 3);
        subjectIDs.add((Long) entityManager.persistAndGetId(sub3));
        entityManager.flush();
    }

    @Test
    public void shouldFindStudentByName() {
        Subject found = subjectRepository.findSubjectByName(NAME1).get();
        Assertions.assertEquals(found.getName(), NAME1);
    }

    @Test
    public void shouldFindSubjectsById() {
        List<Subject> subjects = (List<Subject>) subjectRepository.findAllById(subjectIDs);
        for (Subject s : subjects) {
            Assertions.assertTrue(subjectIDs.contains(s.getId()));
        }
        Assertions.assertEquals(subjectIDs.size(), subjects.size());
    }

    @Test
    public void shouldFindSubjectsALl() {
        List<Subject> list = subjectRepository.findAll();
        Assertions.assertEquals(list.size(), subjectIDs.size());
    }

    @Test
    public void shouldFindSubjectsBySpecialtyAndYear() {
        List<Subject> subjects = subjectRepository.findSubjectsBySpecialityAndYear(SPECIALTY1, 1);
        for (Subject s : subjects) {
            Assertions.assertEquals(s.getSpeciality(), SPECIALTY1);
            Assertions.assertEquals(s.getYear(), 1);
        }
    }

    @Test
    public void shouldFindSubjectsByIDNotIn() {
        List<Long> ids = new ArrayList<>();
        ids.add(subjectIDs.get(1));
        ids.add(subjectIDs.get(2));
        List<Subject> subjects = subjectRepository.findSubjectsByIdNotIn(ids);
        for (Subject s : subjects) {
            Assertions.assertFalse(ids.contains(s.getId()));
        }
    }

    @Test
    public void shouldFailAddSubject() {
        Subject sub1 = new Subject();
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            entityManager.persistAndFlush(sub1);
        });

    }


}
