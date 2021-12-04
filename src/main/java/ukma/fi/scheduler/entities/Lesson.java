package ukma.fi.scheduler.entities;

import lombok.Data;

import javax.persistence.*;

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
    private Integer dayOfWeek;

    @Column(nullable = false)
    private Integer lessonNumber;

    @Column(nullable = false)
    private Integer groupNumber;

}
