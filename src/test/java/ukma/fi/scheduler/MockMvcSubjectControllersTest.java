package ukma.fi.scheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcSubjectControllersTest {

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
    public void shouldReturnShowAllViewToAll() throws Exception {
        mockMvc.perform(get("/subject/showAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("subjects"))
                .andExpect(model().attributeExists("subjects"))
                .andExpect(model().attributeExists("faculties")
                );
    }

    @Test
    public void shouldReturnViewWithSubject() throws Exception {
        mockMvc.perform(get("/subject/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("subject"))
                .andExpect(model().attributeExists("subject"));

    }

}
