package ukma.fi.scheduler.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Subject {
    @Id
    private Long id;
    private String name;

    @OneToOne
    private Faculty faculty;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Lesson> lessons = new ArrayList<>();
}
