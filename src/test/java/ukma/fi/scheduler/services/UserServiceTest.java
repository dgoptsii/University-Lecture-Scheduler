package ukma.fi.scheduler.services;

import com.sun.media.sound.InvalidDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ukma.fi.scheduler.controller.dto.SubjectGroupDTO;
import ukma.fi.scheduler.controller.dto.SubjectGroupListDTO;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.repository.SubjectRepository;
import ukma.fi.scheduler.repository.UserRepository;
import ukma.fi.scheduler.service.UserService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private SubjectRepository subjectRepository;


    @Autowired
    private UserService userService;

    private final User student = new User();

    private final Subject subj1 = new Subject();

    private final Subject subj2 = new Subject();

    private final User teacher = new User();

    {
        student.setLogin("login1");
        student.setPassword("pass");
        student.setStatus("STUDENT");
        student.setSpeciality("IPZ");
        student.setYear(1);
        student.setId(1L);

        teacher.setLogin("teach1");
        teacher.setPassword("pass");
        teacher.setStatus("TEACHER");
        teacher.setId(2L);

        subj1.setName("Sub1");
        subj1.setMaxGroups(3);
        subj1.setSpeciality("IPZ");
        subj1.setYear(1);
        subj1.setId(1L);

        subj2.setName("Sub2");
        subj2.setMaxGroups(3);
        subj2.setSpeciality("Math");
        subj2.setYear(2);
        subj2.setId(2L);

        Map<Subject, Integer> groups = new HashMap<>();
        groups.put(subj2, 3);
        student.setGroups(groups);
    }


    @BeforeEach
    public void mockService() {
        doReturn(Optional.of(subj1)).when(subjectRepository).findById(1L);
        doReturn(Optional.of(subj2)).when(subjectRepository).findById(2L);
        doReturn(Optional.of(subj1)).when(subjectRepository).findSubjectByName("Sub1");
        List<Subject> normSubs = new ArrayList<>();
        normSubs.add(subj1);
        doReturn(normSubs).when(subjectRepository).findSubjectsBySpecialityAndYear(any(String.class), anyInt());
        doReturn(Optional.of(student)).when(userRepository).findByLogin("login1");
        doReturn(Optional.of(teacher)).when(userRepository).findByLogin("teach1");
        doReturn(new User()).when(userRepository).save(any(User.class));
    }


    @Test
    public void shouldFindExistingUserByLogin() {
        assertEquals("login1", userService.findUserByLogin("login1").getLogin());
    }

    @Test
    public void shouldReturnNullTryingToFindNotExistingUser() {
        Assertions.assertNull(userService.findUserByLogin("notExistingLogin"));
    }

    @Test
    public void shouldEditSubjectGroup() throws InvalidDataException {
        SubjectGroupListDTO subjectGroupListDTO = new SubjectGroupListDTO();
        SubjectGroupDTO dto = new SubjectGroupDTO();
        SubjectGroupDTO dto2 = new SubjectGroupDTO();
        dto.setGroupNum(2);
        dto.setSubId(1L);
        dto.setSubName("Sub1");

        dto2.setGroupNum(1);
        dto2.setSubId(2L);
        dto2.setSubName("Sub2");

        subjectGroupListDTO.add(dto);
        subjectGroupListDTO.add(dto2);
        userService.editSubjectGroup("login1", subjectGroupListDTO);
    }

    @Test
    public void shouldFindNormativeSubjects(){
        assertEquals(1, userService.findNormativeSubjects("login1").size());
        assertEquals(1L, userService.findNormativeSubjects("login1").get(0).getId());
    }

    @Test
    public void shouldFinNonNormativeSubjects(){
        assertEquals(1, userService.findNonNormativeSubjects("login1").size());
        assertEquals(2L, userService.findNonNormativeSubjects("login1").get(0).getId());
    }


}
