package ukma.fi.scheduler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ukma.fi.scheduler.entities.Faculty;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.repository.SubjectRepository;

import javax.persistence.PersistenceException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
public class DataJpaSubjectTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SubjectRepository subjectRepository;
    static final String NAME1 = "Subject1";
    static final String NAME2 = "SubjectNew";
    static Long faculty_id;


    @BeforeEach
    public void createBD() {
        Faculty fac = new Faculty();
        fac.setName("Fac");
        faculty_id = (Long) entityManager.persistAndGetId(fac);
        Subject sub1 = new Subject();
        sub1.setName("Subject1");
        sub1.setFaculty(fac);
        sub1.setNormative("N");
        Subject sub2 = new Subject();
        sub2.setName("Subject2");
        sub2.setFaculty(fac);
        sub2.setNormative("N");
        Subject sub3 = new Subject();
        sub3.setName("Subject3");
        sub3.setFaculty(fac);
        sub3.setNormative("N");
        entityManager.persist(sub1);
        entityManager.persist(sub2);
        entityManager.persist(sub3);
        entityManager.flush();
    }

    @Test
    public void testAdd() {
        Subject found = subjectRepository.findByName(NAME1).get();
        System.out.println(found);
        assertThat(found.getName()).isEqualTo(NAME1);
    }


    @Test
    public void testUpdate() {
        Subject found1 = subjectRepository.findByName(NAME1).get();
        found1.setName(NAME2);
        subjectRepository.save(found1);
        Subject found2 = subjectRepository.findByName(NAME2).get();
        assertThat(found2.getName()).isEqualTo(NAME2);
    }

    @Test
    public void testFindAll() {
        List<Subject> list = subjectRepository.findAll();
        assertThat(list.size() == 3);
    }

    @Test
    void exceptionTesting() {
        Assertions.assertThrows(PersistenceException.class, () -> {
            Subject sub = new Subject();
            sub.setName(NAME1);
            entityManager.persistAndFlush(sub);
        });
    }

    @Test
    public void testFindByFacID() {
        List<Subject> list = subjectRepository.findByFaculty_Id(faculty_id);
        assertThat(list.size() == 3);
    }


}
