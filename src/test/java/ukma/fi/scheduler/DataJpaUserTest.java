package ukma.fi.scheduler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ukma.fi.scheduler.entities.Faculty;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.repository.UserRepository;

import javax.persistence.PersistenceException;

import static org.assertj.core.api.Assertions.assertThat;

@org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
public class DataJpaUserTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;
    static final String LOGIN1 = "User1";
    static final String LOGIN2 = "UserNew";
    static Long faculty_id;

    @BeforeEach
    public void createBD() {
        Faculty fac = new Faculty();
        fac.setName("Fac");
        faculty_id = (Long) entityManager.persistAndGetId(fac);
        User user1 = new User();
        user1.setLogin("User1");
        user1.setPassword("123");
        user1.setStatus("TEACHER");
        user1.setFaculty(fac);
        User user2 = new User();
        user2.setLogin("User2");
        user2.setPassword("123");
        user2.setStatus("TEACHER");
        user2.setFaculty(fac);
        User user3 = new User();
        user3.setLogin("User3");
        user3.setPassword("123");
        user3.setStatus("TEACHER");
        user3.setFaculty(fac);
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(user3);
        entityManager.flush();
    }

    @Test
    public void shouldFindUser() {
        User found = userRepository.findByLogin(LOGIN1).get();
        System.out.println(found);
        assertThat(found.getLogin()).isEqualTo(LOGIN1);
    }


    @Test
    public void shouldUpdateUser() {
        User found1 = userRepository.findByLogin(LOGIN1).get();
        found1.setLogin(LOGIN2);
        userRepository.save(found1);
        User found2 = userRepository.findByLogin(LOGIN2).get();
        assertThat(found2.getLogin()).isEqualTo(LOGIN2);
    }


    @Test
    void shouldFailAddUser() {
        Assertions.assertThrows(PersistenceException.class, () -> {
            User user = new User();
            user.setLogin(LOGIN1);
            entityManager.persistAndFlush(user);
        });
    }


}
