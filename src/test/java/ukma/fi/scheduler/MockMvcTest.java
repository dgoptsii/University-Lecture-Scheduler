package ukma.fi.scheduler;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ukma.fi.scheduler.controller.SubjectController;
import ukma.fi.scheduler.entities.Faculty;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.repository.SubjectRepository;
import ukma.fi.scheduler.service.LessonService;
import ukma.fi.scheduler.service.SubjectService;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcTest {

    @MockBean
    SubjectService subjectService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "log", password = "pass", authorities = "STUDENT")
    public void myTest1() throws Exception {
        Subject s = new Subject();
        s.setId(1L);
        s.setNormative("Y");
        s.setName("sub1");
        s.setFaculty(new Faculty("Fac1"));
        s.setLessons(new ArrayList<>());
        doReturn(s).when(subjectService).show(anyLong());

        mockMvc.perform(get("/subject/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("subject"))
                .andExpect(model().attributeExists("subject"));

    }
}
