package ukma.fi.scheduler.controller.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class UserDTO {

    @Pattern(regexp = "([a-z]+\\.[a-z]+)@ukma\\.edu\\.ua")
    private String login;

//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    private String password;

    private String name;

    private String surname;

    private String patronim;
}
