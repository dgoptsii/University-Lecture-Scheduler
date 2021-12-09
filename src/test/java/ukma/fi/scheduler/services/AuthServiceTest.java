package ukma.fi.scheduler.services;

import com.sun.media.sound.InvalidDataException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ukma.fi.scheduler.controller.dto.UserDTO;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.exceptionHandlers.exceptions.UserExistsException;
import ukma.fi.scheduler.repository.UserRepository;
import ukma.fi.scheduler.service.AuthService;
import ukma.fi.scheduler.service.UserService;

import javax.persistence.PersistenceException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
public class AuthServiceTest {


    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    private static final String login1 = "login1";

    @BeforeEach
    public void mockService() {
        User u = new User();
        u.setLogin(login1);
        u.setPassword("pass");
        u.setStatus("STUDENT");
        doReturn(Optional.of(u)).when(userRepository).findByLogin(login1);
        doReturn(u).when(userService).findUserByLogin(login1);
    }

    @SneakyThrows
    @Test
    public void shouldAddNewStudent() {
        User k = new User();
        k.setLogin("login2");
        k.setPassword("passw");

        doReturn(k).when(userRepository).save(k);

        Assertions.assertEquals("login2", authService.registration(k, "STUDENT").getLogin());
    }

    @Test
    public void shouldNotAddExistStudent() {
        Assertions.assertThrows(UserExistsException.class, () -> {
            User k = new User();
            k.setLogin(login1);
            k.setPassword("passw");

            doThrow(PersistenceException.class).when(userRepository).save(k);

            authService.registration(k, "STUDENT");
        });
    }

    @SneakyThrows
    @Test
    public void shouldUpdateLoginToExistUser() {
        UserDTO k = new UserDTO();
        k.setLogin("login2");
        k.setPassword("");
        User res = new User();
        res.setLogin(k.getLogin());
        doReturn(res).when(userRepository).save(any(User.class));
        Assertions.assertEquals("login2", authService.editUser(k, login1).getLogin());
    }

    @SneakyThrows
    @Test
    public void shouldUpdatePasswordToExistUser() {
        UserDTO k = new UserDTO();
        k.setPassword("NEWpassword");
        User exist = userService.findUserByLogin(login1);
        User res = new User();
        res.setLogin(exist.getLogin());
        res.setPassword(k.getPassword());

        doReturn(k.getPassword()).when(passwordEncoder).encode(any());
        doReturn(res).when(userRepository).save(any(User.class));

        Assertions.assertEquals("NEWpassword", authService.editUser(k, login1).getPassword());
    }

    @SneakyThrows
    @Test
    public void shouldNoUpdatePasswordToExistUserIfItEqualsToPrev() {

        UserDTO k = new UserDTO();
        k.setPassword("pass");
        User exist = userService.findUserByLogin(login1);

        doReturn(k.getPassword()).when(passwordEncoder).encode(any());
        doReturn(exist).when(userRepository).save(any(User.class));

        Assertions.assertEquals(exist.getPassword(), authService.editUser(k, login1).getPassword());
    }

    @Test
    public void shouldNotUpdateUserToExistUser() {
        UserDTO k = new UserDTO();
        k.setLogin(login1);
        k.setPassword("passw");
        Assertions.assertThrows(UserExistsException.class, () -> {
            authService.editUser(k, login1);
        });
    }
}
