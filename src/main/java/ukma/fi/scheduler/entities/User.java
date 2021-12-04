package ukma.fi.scheduler.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Map;

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

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    private String patronim;

    private String speciality;

    private Integer year;

    @ElementCollection
    @MapKeyColumn(name = "subjectId")
    @Column(name = "groupNumber")
    private Map<Subject, Integer> groups;


}
