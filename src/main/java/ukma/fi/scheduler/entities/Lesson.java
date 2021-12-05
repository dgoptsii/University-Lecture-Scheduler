package ukma.fi.scheduler.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Data
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private User teacher;

    @Column(nullable = false)
    @Min(1)
    @Max(6)
    private Integer dayOfWeek;

    @Column(nullable = false)
    @Min(1)
    @Max(7)
    private Integer lessonNumber;

    @Column(nullable = false)
    @Min(1)
    private Integer groupNumber;


    public Lesson(Subject subject, Integer dayOfWeek, Integer lessonNumber, User teacher, Integer groupNumber) {
        this.subject = subject;
        this.dayOfWeek = dayOfWeek;
        this.lessonNumber = lessonNumber;
        this.teacher = teacher;
        this.groupNumber = groupNumber;
    }

    public Lesson() {

    }
}
