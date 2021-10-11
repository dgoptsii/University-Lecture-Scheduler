package ukma.fi.scheduler.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;


    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne(optional = false)
    private Faculty faculty;

    //normative or not
    @Column(nullable = false)
    private String normative;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Lesson> lessons = new ArrayList<>();
}
