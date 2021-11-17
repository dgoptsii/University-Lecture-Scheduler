package ukma.fi.scheduler.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import ukma.fi.scheduler.controller.dto.SubjectDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne(optional = false)
    private Faculty faculty;

    @Column(nullable = false)
    private String normative;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="subject")
    private List<Lesson> lessons = new ArrayList<>();

    public Subject(String name, Faculty faculty, String normative) {
        this.name = name;
        this.faculty = faculty;
        this.normative = normative;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", faculty=" + faculty +
                ", normative='" + normative + '\'' +
                ", lessons=" + lessons.stream().map(Lesson::toString).collect(Collectors.toList())+
                '}';
    }
}
