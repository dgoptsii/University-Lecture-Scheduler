package ukma.fi.scheduler.entities;

import lombok.Data;
import ukma.fi.scheduler.controller.dto.UserDTO;

import javax.persistence.*;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;


    @Column(nullable = false)
    private String password;

    // STUDENT, ADMIN, TEACHER

    @Column(nullable = false)
    private String status;

    @ManyToOne(optional = false)
    private Faculty faculty;

}
