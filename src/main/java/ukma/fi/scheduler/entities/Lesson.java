package ukma.fi.scheduler.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Data
public class Lesson {
    @Id
    private Long id;

    private String dayOfWeek;

    private Integer lessonNumber;

    private Integer groupNumber;

    @ManyToOne
    private Subject subject;

    @OneToOne
    private Faculty faculty;
}
