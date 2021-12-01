package ukma.fi.scheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ukma.fi.scheduler.controller.dto.SubjectDTO;
import ukma.fi.scheduler.entities.Faculty;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.repository.FacultyRepository;
import ukma.fi.scheduler.repository.SubjectRepository;
import ukma.fi.scheduler.service.SubjectService;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcSubjectSpringSecurity {

    @MockBean
    SubjectService subjectService;

    @MockBean
    SubjectRepository subjectRepository;

    @MockBean
    FacultyRepository facultyRepository;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void mockService() {
        Subject s = new Subject();
        s.setId(5L);
        s.setNormative("Y");
        s.setName("sub1");
        s.setFaculty(new Faculty("Fac1"));
        s.setLessons(new ArrayList<>());
        doReturn(s).when(subjectService).show(anyLong());
        doReturn(s).when(subjectRepository).save(any(Subject.class));
        doNothing().when(subjectRepository).deleteById(anyLong());
        doReturn(Optional.of(new Faculty())).when(facultyRepository).findById(anyLong());
        doReturn(true).when(subjectService).delete(anyLong());
        doReturn(new ArrayList<>()).when(facultyRepository).findAll();
    }

    @Test
    @WithMockUser(username = "log", password = "pass", authorities = "STUDENT")
    public void shouldReturnViewWithSubject() throws Exception {
        mockMvc.perform(get("/subject/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("subject"))
                .andExpect(model().attributeExists("subject"));

    }

    @Test
    @WithMockUser(username = "student", password = "pass", authorities = "STUDENT")
    public void shouldNotReturnSubjectEditViewToStudent() throws Exception {
        mockMvc.perform(get("/teacher/subject/1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "teacher", password = "pass", authorities = "TEACHER")
    public void shouldReturnSubjectEditViewAndModelToTeacher() throws Exception {
        mockMvc.perform(get("/teacher/subject/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("subject_edit"))
                .andExpect(model().attributeExists("subject"))
                .andExpect(model().attributeExists("faculties")
                );
    }

    @Test
    @WithMockUser(username = "teacher", password = "pass", authorities = "TEACHER")
    public void shouldDeleteAndRedirectForTeacher() throws Exception {
        mockMvc.perform(delete("/teacher/subject/1").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/subject/showAll")
                );
    }

    @Test
    @WithMockUser(username = "student", password = "pass", authorities = "STUDENT")
    public void shouldNotDeleteAndRedirectForStudent() throws Exception {
        mockMvc.perform(delete("/teacher/subject/1").with(csrf()))
                .andExpect(status().is4xxClientError()
                );
    }


    @Test
    @WithMockUser(username = "teacher", password = "pass", authorities = "TEACHER")
    public void shouldRedirectAfterSubjectCreationForTeacher() throws Exception {
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setFacultyId(1L);
        subjectDTO.setName("sub1231");
        subjectDTO.setNormative("N");
        mockMvc.perform(post("/teacher/subject/add").flashAttr("subject", subjectDTO).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/subject/showAll"));
    }

    @Test
    @WithMockUser(username = "teacher", password = "pass", authorities = "TEACHER")
    public void shouldRedirectAfterSubjectEditingForTeacher() throws Exception {
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setFacultyId(1L);
        subjectDTO.setName("sub1231");
        subjectDTO.setNormative("N");
        mockMvc.perform(put("/teacher/subject/1").flashAttr("subject", subjectDTO).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/subject/1"));
    }


    @Test
    @WithMockUser(username = "student", password = "pass", authorities = "STUDENT")
    public void shouldNotRedirectAfterSubjectCreationForStudent() throws Exception {
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setFacultyId(1L);
        subjectDTO.setName("sub1231");
        subjectDTO.setNormative("N");
        mockMvc.perform(post("/teacher/subject/add").flashAttr("subject", subjectDTO).with(csrf()))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "student", password = "pass", authorities = "STUDENT")
    public void shouldNotRedirectAfterSubjectEditingForStudent() throws Exception {
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setFacultyId(1L);
        subjectDTO.setName("sub1231");
        subjectDTO.setNormative("N");
        mockMvc.perform(put("/teacher/subject/1").flashAttr("subject", subjectDTO).with(csrf()))
                .andExpect(status().is4xxClientError());
    }

}
