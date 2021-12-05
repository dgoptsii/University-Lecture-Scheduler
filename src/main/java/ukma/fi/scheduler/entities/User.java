package ukma.fi.scheduler.entities;

import lombok.Data;
import ukma.fi.scheduler.controller.dto.UserDTO;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Map;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank
    @Pattern(regexp = "([a-z]+\\.[a-z]+)@ukma\\.edu\\.ua")
    private String login;

    @Column(nullable = false)
    @NotBlank
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    private String password;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    @NotBlank
    private String surname;

    private String patronim;

    private String speciality;

    private Integer year;

    @ElementCollection
    @MapKeyColumn(name = "subjectId")
    @Column(name = "groupNumber")
    private Map<Subject, Integer> groups;


    public User changeUser(UserDTO user){
        this.name = user.getName();
        this.surname = user.getSurname();
        this.patronim = user.getPatronim();
        this.login = user.getLogin();
        return this;
    }
}
