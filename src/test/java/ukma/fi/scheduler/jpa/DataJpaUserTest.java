package ukma.fi.scheduler.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.repository.SubjectRepository;
import ukma.fi.scheduler.repository.UserRepository;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
public class DataJpaUserTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

     final String LOG1 = "log.a@ukma.edu.ua";
     final String LOG2 = "log.b@ukma.edu.ua";
     final String LOG3 = "log.c@ukma.edu.ua";
     final String LOG4 = "log.d@ukma.edu.ua";

     final String STATUS_STUDENT = "STUDENT";
    static final String STATUS_TEACHER = "STUDENT";

     final String PASSWORD = "Password123";


     List<Long>  userIDs = new ArrayList<>();

    @BeforeEach
    public void createBD() {

        User tech1 = new User(LOG1,"N1", "S1", STATUS_STUDENT,  PASSWORD);
        User tech2 = new User(LOG2,"N2", "S2", STATUS_STUDENT,  PASSWORD);
        User tech3 = new User(LOG3,"N3", "S3", STATUS_TEACHER,  PASSWORD);
        User tech4 = new User(LOG4,"N4", "S4", STATUS_TEACHER,  PASSWORD);


        userIDs.add((Long) entityManager.persistAndGetId(tech1));
        userIDs.add((Long) entityManager.persistAndGetId(tech2));
        userIDs.add((Long) entityManager.persistAndGetId(tech3));
        userIDs.add((Long) entityManager.persistAndGetId(tech4));
        entityManager.flush();
    }


    @Test
    public void shouldFindUserByLogin(){
        User found = userRepository.findByLogin(LOG1).get();
        Assertions.assertEquals(found.getLogin(),LOG1);
    }

    @Test
    public void shouldFailAddUser(){
        Assertions.assertThrows(PersistenceException.class, () -> {
            User tech4 = new User();
            tech4.setName("N4");
            tech4.setSurname("S4");
            tech4.setStatus("TEACHER");
            tech4.setPassword(PASSWORD);
            tech4.setLogin(LOG4);
            entityManager.persistAndFlush(tech4);
        });
    }

    @Test
    public void shouldFailValidationAddUser(){
        User tech = new User();
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            entityManager.persistAndFlush(tech);
        });
        tech.setName("N1");
        tech.setSurname("S1");
        tech.setStatus("TEACHER");
        tech.setPassword(PASSWORD);
        tech.setLogin("Log");
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            entityManager.persistAndFlush(tech);
        });
    }

    @Test
    public void shouldFindUserStatus(){
        List <User> teachers = userRepository.findUsersByStatus(STATUS_TEACHER);
        for (User t: teachers){
            Assertions.assertEquals(t.getStatus(),STATUS_TEACHER);
        }
        List <User> students = userRepository.findUsersByStatus(STATUS_STUDENT);
        for (User s: students){
            Assertions.assertEquals(s.getStatus(),STATUS_STUDENT);
        }
    }


}
