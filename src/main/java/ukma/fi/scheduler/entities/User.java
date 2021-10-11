package ukma.fi.scheduler.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;

    private String password;

    // STUDENT, ADMIN, TEACHER
    private String status;

    @OneToOne
    private Faculty faculty;
}
