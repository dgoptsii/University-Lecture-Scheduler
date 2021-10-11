package ukma.fi.scheduler.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    private Integer weekNumber;

    private String dayOfWeek;

    private Integer number;

    @OneToOne(optional = false)
    private Lesson lesson;
}
