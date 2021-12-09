package ukma.fi.scheduler.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.repository.UserRepository;
import ukma.fi.scheduler.service.UserService;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class LessonServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void mockService() {
        User u = new User();
        u.setLogin("login1");
        u.setPassword("pass");
        u.setStatus("STUDENT");

        doReturn(Optional.of(u)).when(userRepository).findByLogin("login1");
        doReturn(new ArrayList<User>()).when(userRepository).findAllByStatus("STUDENT");
        doReturn(u).when(userRepository).save(any(User.class));
    }


    @Test
    public void shouldFindExistingUserByLogin() {
        Assertions.assertEquals("login1", userService.findUserByLogin("login1").getLogin());
    }

    @Test
    public void shouldReturnNullTryingToFindNotExistingUser(){
        Assertions.assertNull(userService.findUserByLogin("notExistingLogin"));
    }

}
