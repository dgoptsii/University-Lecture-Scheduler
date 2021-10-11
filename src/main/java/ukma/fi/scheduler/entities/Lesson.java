package ukma.fi.scheduler.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String dayOfWeek;

    @Column(nullable = false)
    private Integer lessonNumber;

    @Column(nullable = false)
    private Integer groupNumber;

    @ManyToOne(optional = false)
    private Subject subject;

    public Lesson(Subject subject, Integer groupNumber, Integer lessonNumber, String dayOfWeek) {
        this.subject = subject;
        this.groupNumber = groupNumber;
        this.lessonNumber = lessonNumber;
        this.dayOfWeek = dayOfWeek;
    }

}
