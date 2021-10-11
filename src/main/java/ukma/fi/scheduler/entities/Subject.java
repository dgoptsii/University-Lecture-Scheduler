package ukma.fi.scheduler.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToOne
    private Faculty faculty;

    //normative or not
    private String normative;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Lesson> lessons = new ArrayList<>();

    public Subject(String name, Faculty faculty, String normative) {
        this.name = name;
        this.faculty = faculty;
        this.normative = normative;
    }
}
