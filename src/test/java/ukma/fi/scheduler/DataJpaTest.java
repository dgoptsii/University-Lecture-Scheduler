package ukma.fi.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ukma.fi.scheduler.entities.Faculty;
import ukma.fi.scheduler.repository.FacultyRepository;

import static org.assertj.core.api.Assertions.assertThat;

@org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
public class DataJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FacultyRepository facultyRepository;

    @Test
    public void test() {
        Faculty faculty = new Faculty();
        faculty.setName("Fac1");
        entityManager.persistAndFlush(faculty);
        Faculty found = facultyRepository.findByName(faculty.getName()).get();
        assertThat(found.getName()).isEqualTo(faculty.getName());
    }
}
