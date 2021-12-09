package ukma.fi.scheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ukma.fi.scheduler.controller.dto.SubjectGroupListDTO;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.service.ScheduleService;
import ukma.fi.scheduler.service.SubjectService;
import ukma.fi.scheduler.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcSubjectControllersTest {

    @MockBean
    SubjectService subjectService;

    @MockBean
    UserService userService;


    @MockBean
    ScheduleService  scheduleService;


    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void mockService() throws Exception {

        doReturn(new SubjectGroupListDTO()).when(userService).getSubjectGroupDTOS(any(String.class));
        doNothing().when(userService).editSubjectGroup(any(String.class),any(SubjectGroupListDTO.class));
        doReturn(new ArrayList<Subject>()).when(userService).findNonNormativeFreeSubjects(any(String.class));
        doReturn(new ArrayList<Subject>()).when(userService).findNonNormativeSubjects(any(String.class));
        doReturn(new ArrayList<Subject>()).when(userService).findNormativeSubjects(any(String.class));
        doNothing().when(userService).addNonNormativeGroup(any(String.class),any(Long.class));
        doNothing().when(userService).deleteNonNormativeGroup(any(String.class),any(Long.class));
        doReturn(new HashMap<String, Set< Lesson >>()).when(scheduleService).findLessonsForStudent(any(String.class));
    }


    @Test
    @WithMockUser(authorities = "STUDENT")
    public void shouldReturnViewWithSubjectGroups() throws Exception {
        mockMvc.perform(get("/student/subject/groups"))
                .andExpect(status().isOk())
                .andExpect(view().name("student-add-group"))
                .andExpect(model().attributeExists("form"));

    }

    @Test
    @WithMockUser(authorities = "TEACHER")
    public void shouldNotReturnViewWithSubjectGroups() throws Exception {
        mockMvc.perform(get("/student/subject/groups"))
                .andExpect(status().is4xxClientError());
    }


    @Test
    @WithMockUser( authorities = "STUDENT")
    public void shouldRedirectAfterStudentSubjectGroup() throws Exception {
        SubjectGroupListDTO form = new SubjectGroupListDTO();
        mockMvc.perform(post("/student/subject/groups").flashAttr("form", form).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/student/subject/groups"));
    }


    @Test
    @WithMockUser( authorities = "TEACHER")
    public void shouldNotRedirectAfterStudentSubjectGroup() throws Exception {
        SubjectGroupListDTO form = new SubjectGroupListDTO();
        mockMvc.perform(post("/student/subject/groups").flashAttr("form", form).with(csrf()))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(authorities = "STUDENT")
    public void shouldReturnViewWithSubjectAdd() throws Exception {
        mockMvc.perform(get("/student/subject/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("student-add-subject"))
                .andExpect(model().attributeExists("addSubjects"))
                .andExpect(model().attributeExists("userSubjects"))
                .andExpect(model().attributeExists("userNormative"));
    }

    @Test
    @WithMockUser(authorities = "TEACHER")
    public void shouldNotReturnViewWithSubjectAdd() throws Exception {
        mockMvc.perform(get("/student/subject/add"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(authorities = "STUDENT")
    public void shouldRedirectAfterStudentSubjectAdded() throws Exception {
        mockMvc.perform(post("/student/subject/1").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/student/subject/add"));
    }

    @Test
    @WithMockUser( authorities = "TEACHER")
    public void shouldNotRedirectAfterStudentSubjectAdded() throws Exception {
        mockMvc.perform(post("/student/subject/1").with(csrf()))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser( authorities = "STUDENT")
    public void shouldDeleteAndRedirectForStudentSubject() throws Exception {
        mockMvc.perform(delete("/student/subject/1").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/student/subject/add")
                );
    }

    @Test
    @WithMockUser( authorities = "TEACHER")
    public void shouldNotDeleteAndRedirectForStudentSubject() throws Exception {
        mockMvc.perform(delete("/student/subject/1").with(csrf()))
                .andExpect(status().is4xxClientError());
    }


    @Test
    @WithMockUser(authorities = "STUDENT")
    public void shouldReturnViewWithStudentScheduler() throws Exception {
        mockMvc.perform(get("/student/scheduler"))
                .andExpect(status().isOk())
                .andExpect(view().name("schedule"))
                .andExpect(model().hasNoErrors()) ;
    }


    @Test
    @WithMockUser(authorities = "TEACHER")
    public void shouldNotReturnViewWithStudentScheduler() throws Exception {
        mockMvc.perform(get("/student/subject/scheduler"))
                .andExpect(status().is4xxClientError());
    }

}
