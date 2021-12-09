package ukma.fi.scheduler.controller;

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
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.service.AuthService;
import ukma.fi.scheduler.service.UserService;

import javax.validation.Valid;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcAdminControllerTest {

    @MockBean
    AuthService authService;


    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void mockService() throws Exception {
        doReturn(new User()).when(authService).registration(any(User.class),any(String.class));
    }


    @Test
    @WithMockUser(authorities = "ADMIN")
    public void shouldReturnViewAdminAddTeacher() throws Exception {
        mockMvc.perform(get("/admin/add_teacher").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("teacher_registration"))
                .andExpect(model().hasNoErrors());
    }

    @Test
    @WithMockUser(authorities = "TEACHER")
    public void shouldNotReturnViewAdminAddTeacher() throws Exception {
        mockMvc.perform(get("/admin/add_teacher").with(csrf()))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser( authorities = "ADMIN")
    public void shouldRedirectAfterAdminAddTeacher() throws Exception {
        User teacher = new User("a.a@ukma.edu.ua", "name", "surname","TEACHER","Secret10");
        mockMvc.perform(post("/admin/add_teacher").flashAttr("user",teacher).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/add_teacher"));
    }


    @Test
    @WithMockUser(authorities = "TEACHER")
    public void shouldNotRedirectAfterAdminAddTeacher() throws Exception {
        User teacher = new User("a.a@ukma.edu.ua", "name", "surname","TEACHER","Secret10");
        mockMvc.perform(post("/admin/add_teacher").flashAttr("user",teacher).with(csrf()))
                .andExpect(status().is4xxClientError());
    }


  


}
