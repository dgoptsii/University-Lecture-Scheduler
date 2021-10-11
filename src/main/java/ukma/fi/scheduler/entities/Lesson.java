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

    @Column(nullable = false)
    private String dayOfWeek;

    @Column(nullable = false)
    private Integer lessonNumber;

    @Column(nullable = false)
    private Integer groupNumber;

    @ManyToOne(optional = false)
    private Subject subject;

}
