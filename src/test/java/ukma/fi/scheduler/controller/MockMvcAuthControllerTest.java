package ukma.fi.scheduler.controller;

import com.sun.media.sound.InvalidDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ukma.fi.scheduler.controller.dto.UserDTO;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.service.AuthService;
import ukma.fi.scheduler.service.UserService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcAuthControllerTest {

    @MockBean
    UserService userService;

    @MockBean
    private AuthService authService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void mockService() throws Exception {
        User teacher = new User("a.a@ukma.edu.ua", "nameA", "surname", "TEACHER", "Secret10");
        User student = new User("b.b@ukma.edu.ua", "nameB", "surname", "STUDENT", "Secret10");
        doReturn(teacher).when(userService).findUserByLogin("a.a@ukma.edu.ua");
        doReturn(student).when(userService).findUserByLogin("b.b@ukma.edu.ua");
        doThrow(new InvalidDataException()).when(authService).editUser(any(UserDTO.class), any(String.class));
    }

    @Test
    public void contextLoads() {
        assertThat(userService).isNotNull();
        assertThat(authService).isNotNull();
    }

    @Test
    @WithMockUser(username = "b.b@ukma.edu.ua", password = "Secret10", authorities = "STUDENT")
    public void shouldReturnViewWithStudentProfile() throws Exception {
        mockMvc.perform(get("/profile").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("student-profile"))
                .andExpect(model().hasNoErrors());
    }

    @Test
    @WithMockUser(username = "a.a@ukma.edu.ua", password = "Secret10", authorities = "TEACHER")
    public void shouldReturnViewWithTeacherProfile() throws Exception {
        mockMvc.perform(get("/profile").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("teacher-profile"))
                .andExpect(model().hasNoErrors());
    }

    @Test
    @WithMockUser(username = "a.a@ukma.edu.ua", password = "Secret10", authorities = "TEACHER")
    public void shouldReturnViewWithProfileEdit() throws Exception {
        mockMvc.perform(get("/profile_edit").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("profile-edit"));
    }

    @Test
    public void shouldRedirectAfterProfileEdited() throws Exception {
        mockMvc.perform(put("/profile_edit").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void shouldReturnViewWithRegistration() throws Exception {
        mockMvc.perform(get("/registration").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("student_registration"))
                .andExpect(model().hasNoErrors());
    }

    @Test
    public void shouldRedirectAfterRegistration() throws Exception {
        User student = new User("b.b@ukma.edu.ua", "nameB", "surname", "STUDENT", "Secret10");
        mockMvc.perform(post("/registration").flashAttr("user", student).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

}
