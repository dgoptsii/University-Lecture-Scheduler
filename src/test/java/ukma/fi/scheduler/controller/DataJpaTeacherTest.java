package ukma.fi.scheduler.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;

@org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
public class DataJpaTeacherTest {

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
        User tech1 = new User();
        User tech2 = new User();
        User tech3 = new User();

        entityManager.flush();
    }



}
