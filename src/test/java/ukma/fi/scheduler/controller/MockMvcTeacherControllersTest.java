package ukma.fi.scheduler.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ukma.fi.scheduler.controller.dto.LessonDTO;
import ukma.fi.scheduler.controller.dto.SubjectLectureDTO;
import ukma.fi.scheduler.entities.*;
import ukma.fi.scheduler.service.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcTeacherControllersTest {

    @MockBean
    SubjectService subjectService;

    @MockBean
    LessonService lessonService;

    @MockBean
    UserService userService;


    @MockBean
    ScheduleService scheduleService;


    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void mockService() throws Exception {
        doReturn(new Subject()).when(subjectService).create(new SubjectLectureDTO());
        doReturn(new ArrayList<User>()).when(userService).findByRole("TEACHER");
        doReturn(new ArrayList<User>()).when(userService).findAllTeachers();
        doReturn(new ArrayList<Subject>()).when(subjectService).findAll();
        doReturn(new Lesson()).when(lessonService).create(new LessonDTO());
        doReturn(new Subject()).when(subjectService).findSubjectById(any(Long.class));
        doReturn(new ArrayList<Lesson>()).when(lessonService).findAllBySubject_Id(1L);
        doNothing().when(subjectService).edit(any(Long.class),any(Subject.class));
        doNothing().when(subjectService).deleteSubject(any(Long.class));
        doReturn(new Lesson()).when(lessonService).findById(any(Long.class));
        doNothing().when(lessonService).delete(any(Long.class));
        doNothing().when(lessonService).edit(any(Long.class),any(Lesson.class));
        doReturn(new HashMap<String, Set<Lesson>>()).when(scheduleService).findLessonsForTeacher(any(String.class));
    }

    @Test
    public void contextLoads() {
        assertThat(subjectService).isNotNull();
        assertThat(lessonService).isNotNull();
        assertThat(userService).isNotNull();
        assertThat(scheduleService).isNotNull();
    }

    @Test
    @WithMockUser(authorities = "TEACHER")
    public void shouldReturnViewWithAllSubjects() throws Exception {
        mockMvc.perform(get("/teacher/subject/all").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("teacher-all-subjects"))
                .andExpect(model().hasNoErrors());
    }

    @Test
    @WithMockUser(authorities = "STUDENT")
    public void shouldNotReturnViewWithAllSubjects() throws Exception {
        mockMvc.perform(get("/teacher/subject/all").with(csrf()))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(authorities = "TEACHER")
    public void shouldReturnViewWithSubjectAdd() throws Exception {
        mockMvc.perform(get("/teacher/subject/add").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("teacher_add_subject"))
                .andExpect(model().attributeExists("specialties"))
                .andExpect(model().attributeExists("teachers"));
    }

    @Test
    @WithMockUser(authorities = "STUDENT")
    public void shouldNotReturnViewWithSubjectAdd() throws Exception {
        mockMvc.perform(get("/teacher/subject/add").with(csrf()))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(authorities = "TEACHER")
    public void shouldRedirectAfterTeacherSubjectEdited() throws Exception {
        Subject subject = new Subject("name", 3, "Math", 4);
        mockMvc.perform(put("/teacher/subject/1").flashAttr("subject",subject).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/teacher/subject/1"));
    }

    @Test
    @WithMockUser(authorities = "STUDENT")
    public void shouldNotRedirectAfterTeacherSubjectEdited() throws Exception {
        mockMvc.perform(put("/teacher/subject/1").with(csrf()))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser( authorities = "TEACHER")
    public void shouldDeleteAndRedirectForTeacherSubject() throws Exception {
        mockMvc.perform(delete("/teacher/subject/1").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/teacher/subject/all")
                );
    }

    @Test
    @WithMockUser( authorities = "STUDENT")
    public void shouldNotDeleteAndRedirectForTeacherSubject() throws Exception {
        mockMvc.perform(delete("/teacher/subject/1").with(csrf()))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(authorities = "TEACHER")
    public void shouldReturnViewWithLessonAdd() throws Exception {
        mockMvc.perform(get("/teacher/lesson/add").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("teacher_add_lesson"))
                .andExpect(model().attributeExists("specialties"))
                .andExpect(model().attributeExists("subjects"))
                .andExpect(model().attributeExists("teachers"));
    }

    @Test
    @WithMockUser(authorities = "STUDENT")
    public void shouldNotReturnViewWithLessonAdd() throws Exception {
        mockMvc.perform(get("/teacher/lesson/add").with(csrf()))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(authorities = "TEACHER")
    public void shouldRedirectAfterTeacherLessonEdited() throws Exception {
        Subject subject = new Subject("name", 3, "Math", 4);
        User teacher = new User("a.a@ukma.edu.ua", "name", "surname","TEACHER","Secret10");
        Lesson lesson = new Lesson(subject, 1, 1, teacher, 1);
        mockMvc.perform(put("/teacher/lesson/1").flashAttr("lesson",lesson).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/teacher/subject/"+subject.getId()));
    }

    @Test
    @WithMockUser(authorities = "STUDENT")
    public void shouldNotRedirectAfterTeacherLessonEdited() throws Exception {
        mockMvc.perform(put("/teacher/lesson/1").with(csrf()))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(authorities = "TEACHER")
    public void shouldReturnViewWithStudentScheduler() throws Exception {
        mockMvc.perform(get("/teacher/scheduler"))
                .andExpect(status().isOk())
                .andExpect(view().name("schedule"))
                .andExpect(model().hasNoErrors()) ;
    }

    @Test
    @WithMockUser(authorities = "STUDENT")
    public void shouldNotReturnViewWithStudentScheduler() throws Exception {
        mockMvc.perform(get("/teacher/subject/scheduler"))
                .andExpect(status().is4xxClientError());
    }

}
