package ukma.fi.scheduler.controller;

import com.sun.media.sound.InvalidDataException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ukma.fi.scheduler.controller.dto.LessonDTO;
import ukma.fi.scheduler.controller.dto.SubjectLectureDTO;
import ukma.fi.scheduler.controller.dto.UserDTO;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.exceptionHandlers.exceptions.InvalidData;
import ukma.fi.scheduler.exceptionHandlers.exceptions.SubjectNotFoundException;
import ukma.fi.scheduler.repository.LessonRepository;
import ukma.fi.scheduler.repository.SubjectRepository;
import ukma.fi.scheduler.repository.UserRepository;
import ukma.fi.scheduler.service.AuthService;
import ukma.fi.scheduler.service.LessonService;
import ukma.fi.scheduler.service.SubjectService;
import ukma.fi.scheduler.service.UserService;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
public class SubjectServiceTest {

    @MockBean
    private SubjectRepository subjectRepository;

    @MockBean
    private LessonRepository lessonRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private LessonService lessonService;

    @Autowired
    private SubjectService subjectService;


    private final String IPZ = "IPZ";
    private final String MATH = "MATH";
    private final String COMP = "COMP";
    @BeforeEach
    public void mockService() {
        Subject one  = new Subject(1L,"sub1",3,IPZ,2);
        Subject two  = new Subject(2L,"sub2",1,MATH,3);
        Subject three  = new Subject(3L,"sub3",0,COMP,1);
        Subject four  = new Subject(4L,"sub4",6,IPZ,2);

        Lesson lecOne = new Lesson();
        lecOne.setSubject(one);
        lecOne.setGroupNumber(0);

        Lesson prOne = new Lesson();
        prOne.setSubject(one);
        prOne.setGroupNumber(2);

        ArrayList<Subject> list = new ArrayList<>();
        list.add(one);
        list.add(two);

        User student = new User();
        student.setLogin("st");
        student.setPassword("pass");
        student.setStatus("STUDENT");
        student.setSpeciality(IPZ);
        student.setYear(2);
        student.getGroups().put(one,2);
        student.getGroups().put(two,1);

        User stUpd = new User(student);
        stUpd.getGroups().remove(two);
        doReturn(Optional.of(stUpd)).when(userRepository).findById(2L);
        doReturn(stUpd).when(userService).findUserById(2L);

        doReturn(new ArrayList<>(Collections.singleton(student))).when(userRepository).findAll();
        doReturn(new ArrayList<>(Collections.singleton(stUpd))).when(userRepository).saveAll(any());

        doReturn(Optional.of(one)).when(subjectRepository).findById(1L);
        doReturn(Optional.of(one)).when(subjectRepository).findSubjectByName("sub1");
        doReturn(Optional.of(two)).when(subjectRepository).findSubjectByName("sub2");
        doReturn(Optional.of(four)).when(subjectRepository).findSubjectByName("sub4");
        doReturn(Optional.of(two)).when(subjectRepository).findById(2L);
        doReturn(Optional.of(three)).when(subjectRepository).findById(3L);
        doReturn(Optional.of(four)).when(subjectRepository).findById(4L);
        doReturn(list).when(subjectRepository).findSubjectsByIdIn(any());
    }

    @Test
    public void shouldFindSubjectByIdThatExist(){
        Assertions.assertEquals("sub1", subjectService.findSubjectById(1L).getName());
    }

    @Test
    public void shouldNotFindSubjectByIdThatIsNotExist(){
        Assertions.assertNull( subjectService.findSubjectById(100L));
    }

    @Test
    public void shouldFindSubjectByNameThatExist(){
        Assertions.assertEquals("sub1", subjectService.findSubjectByName("sub1").getName());
    }

    @Test
    public void shouldNotFindSubjectByNameThatIsNotExist(){
        Assertions.assertNull( subjectService.findSubjectByName("test"));
    }

    @Test
    public void shouldFindAllSubjectsById(){
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        Assertions.assertEquals(2, subjectService.findSubjectByIdIn(ids).size());
    }

    @Test
    public void shouldDeleteSubjectThatIsExist() throws Exception {
        subjectService.deleteSubject(3L);
        Assertions.assertFalse(userService.findUserById(2L).getGroups().containsKey(subjectService.findSubjectById(3L)));
    }

    @SneakyThrows
    @Test
    public void shouldThrowErrorWhenCreateSubjectThatIsExist(){
        SubjectLectureDTO dto = new SubjectLectureDTO();
        dto.setName("sub5");
        dto.setSpecialty(IPZ);
        dto.setYear(3);
        dto.setDayOfWeek(3);
        dto.setLessonNumber(2);
        dto.setTeacher(null);
        Subject five = new Subject("sub5",3,IPZ,3);
        LessonDTO lecture = new LessonDTO(five, dto.getTeacher(), dto.getDayOfWeek(), dto.getLessonNumber(), 0);
        Lesson res = Lesson.createFromDto(lecture);
        doReturn(res).when(lessonService).create(lecture);
//        doReturn(five)(subjectService).findSubjectByName("sub5");
        Assertions.assertNull(subjectService.create(dto));
    }


    @Test
    public void shouldThrowErrorWhenDeleteSubjectThatIsNotExist(){
        Assertions.assertThrows(SubjectNotFoundException.class, () -> {
            subjectService.deleteSubject(100L);
        });
    }

    @Test
    public void shouldThrowErrorWhenEditingSubjectThatIsBroken(){
        Subject test  = new Subject(4L,"sub4",6,IPZ,2);
        Assertions.assertThrows(InvalidData.class, () -> {
            subjectService.edit(1L,test);
        });
    }

    @Test
    public void shouldThrowErrorWhenEditingSubjectThatIsAlreadyExist(){
        Subject twoNew  = new Subject(3L,"sub2",1,MATH,3);
        Assertions.assertThrows(InvalidDataException.class, () -> {
            subjectService.edit(3L,twoNew);
        });
    }

    @SneakyThrows
    @Test
    public void shouldEditSubjectThatIsAlreadyExist(){
        Subject twoNew  = new Subject(3L,"sub3",1,MATH,3);
        doReturn(twoNew).when(subjectRepository).save(any());
        Assertions.assertTrue(subjectService.edit(3L,twoNew));
    }

    @SneakyThrows
    @Test
    public void shouldEditGroupsSubjectThatIsAlreadyExist(){
        Subject twoNew  = new Subject(4L,"sub44",3,MATH,3);
        doReturn(twoNew).when(subjectRepository).save(any());
        Assertions.assertTrue(subjectService.edit(4L,twoNew));
    }
}
