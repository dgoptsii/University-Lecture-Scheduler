package ukma.fi.scheduler.controller.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserDTO {

    @NotNull
    @Pattern(regexp = "([a-z]+\\.[a-z]+)@ukma\\.edu\\.ua")
    private String login;

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    private String password;

    @NotNull
    @Pattern(regexp = "STUDENT|ADMIN|TEACHER")
    private String status;

    @NotNull
    @Min(1)
    private Long facultyId;
}
