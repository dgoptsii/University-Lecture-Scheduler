package ukma.fi.scheduler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ukma.fi.scheduler.entities.Faculty;
import ukma.fi.scheduler.repository.FacultyRepository;

import javax.persistence.PersistenceException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
public class DataJpaFacultyTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FacultyRepository facultyRepository;
    static final String NAME1 = "Fac1";
    static final String NAME2 = "FacNew";


    @BeforeEach
    public void createBD() {
        Faculty faculty = new Faculty();
        faculty.setName("Fac1");
        Faculty faculty1 = new Faculty();
        faculty1.setName("Fac2");
        Faculty faculty2 = new Faculty();
        faculty2.setName("Fac3");
        entityManager.persist(faculty);
        entityManager.persist(faculty1);
        entityManager.persist(faculty2);
        entityManager.flush();
    }

    @Test
    public void testAdd() {
        Faculty found = facultyRepository.findByName(NAME1).get();
        System.out.println(found);
        assertThat(found.getName()).isEqualTo(NAME1);
    }


    @Test
    public void testUpdate() {
        Faculty found1 = facultyRepository.findByName(NAME1).get();
        found1.setName(NAME2);
        facultyRepository.save(found1);
        Faculty found2 = facultyRepository.findByName(NAME2).get();
        assertThat(found2.getName()).isEqualTo(NAME2);
    }

    @Test
    public void testFindAll() {
        List<Faculty> list = facultyRepository.findAll();
        assertThat(list.size() == 3);
    }

    @Test
    void exceptionTesting() {
        Assertions.assertThrows(PersistenceException.class, () -> {
            Faculty faculty = new Faculty();
            faculty.setName(NAME1);
            entityManager.persistAndFlush(faculty);
        });
    }


}
