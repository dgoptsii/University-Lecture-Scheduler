package ukma.fi.scheduler.entities;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ukma.fi.scheduler.controller.dto.UserDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
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
    private Map<Subject, Integer> groups = new HashMap<>();

    public User(String login, String name, String surname,String status,String password){
        this.login=login;
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.password = password;
    }
    public User(User user){
        this.id = user.getId();
        this.login=user.getLogin();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.status = user.getStatus();
        this.password = user.getPassword();
        this.groups = user.getGroups();
    }
    public User changeUser(UserDTO user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.patronim = user.getPatronim();
        this.login = user.getLogin();
        if (!user.getPassword().isEmpty()) {
            this.password = user.getPassword();
        }
        return this;
    }

    public List<Long> getStudentSubjectsId(){
        return this.getGroups().keySet().stream().map(Subject::getId).collect(Collectors.toList());
    }
    public List<Subject> getStudentSubjects(){
        return new ArrayList<>(this.getGroups().keySet());
    }
}
