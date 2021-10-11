package ukma.fi.scheduler.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String dayOfWeek;

    private Integer lessonNumber;

    private Integer groupNumber;

    @ManyToOne
    private Subject subject;

    public Lesson(Subject subject, Integer groupNumber, Integer lessonNumber, String dayOfWeek) {
        this.subject = subject;
        this.groupNumber = groupNumber;
        this.lessonNumber = lessonNumber;
        this.dayOfWeek = dayOfWeek;
    }

}
