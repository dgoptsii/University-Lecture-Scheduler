package ukma.fi.scheduler.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class User {
    @Id
    private Long id;

    private String login;

    private String password;

    // STUDENT, ADMIN, TEACHER
    private String status;

    @OneToOne
    private Faculty faculty;
}
