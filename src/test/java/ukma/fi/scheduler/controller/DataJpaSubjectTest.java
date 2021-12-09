package ukma.fi.scheduler.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
public class DataJpaSubjectTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SubjectRepository subjectRepository;
    static final String NAME1 = "BD";
    static final String NAME2 = "OKA";
    static final String NAME3 = "SP";

    static final String SPECIALTY1 = "IPZ";
    static final String SPECIALTY2 = "MATH";
    static final String SPECIALTY3 = "SPECIALTY";

    static List<Long>  subjectIDs = new ArrayList<>();

    @BeforeEach
    public void createBD() {
        Subject sub1  = new  Subject(NAME1, 1, SPECIALTY1, 1);
        subjectIDs.add((Long) entityManager.persistAndGetId(sub1));
        Subject sub2  = new  Subject(NAME2, 2, SPECIALTY2, 2);
        subjectIDs.add((Long) entityManager.persistAndGetId(sub2));
        Subject sub3  = new  Subject(NAME3, 3, SPECIALTY3, 3);
        subjectIDs.add((Long) entityManager.persistAndGetId(sub3));
        entityManager.flush();
    }

    @Test
    public void shouldFindStudentByName() {
        Subject found = subjectRepository.findSubjectByName(NAME1).get();
        System.out.println(found);
        assertThat(found.getName()).isEqualTo(NAME1);
    }

    @Test
    public void shouldFindSubjectsById(){
        List<Subject> subjects = (List<Subject>) subjectRepository.findAllById(subjectIDs);
        for (Subject s :subjects){
            assertThat(subjectIDs.contains(s.getId()));
        }
        assertThat(subjectIDs.size() == subjects.size());
    }

    @Test
    public void shouldFindSubjectsALl(){
        List<Subject> list = subjectRepository.findAll();
        assertThat(list.size() == subjectIDs.size());
    }

    @Test
    public void shouldFindSubjectsBySpecialtyAndYear(){
        List<Subject> subjects = subjectRepository.findSubjectsBySpecialityAndYear(SPECIALTY1,1);
        for (Subject s :subjects){
            assertThat(s.getSpeciality()).isEqualTo(SPECIALTY1);
            assertThat(s.getYear()== 1L);
        }
    }
    @Test
    public void shouldFindSubjectsByIDNotIn(){
        List<Long> ids = new ArrayList<>();
        ids.add(subjectIDs.get(1));
        ids.add(subjectIDs.get(2));
        List<Subject> subjects = subjectRepository.findSubjectsByIdNotIn(ids);
        for (Subject s :subjects){
            assertThat(!ids.contains(s.getId()));
        }
    }


    //TODO should not be able to insert two similar name-specialty-year
    @Test
    public void shouldFailAddSubject(){
        Subject sub1  = new  Subject(NAME1, 1, SPECIALTY1, 1);
        entityManager.persistAndFlush(sub1);
         //TODO add assertThrows

//        Assertions.assertThrows(PersistenceException.class, () -> {
//            Subject sub = new Subject();
//            sub.setName(NAME1);
//            entityManager.persistAndFlush(sub);
//        });

    }


}
