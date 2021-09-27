package ukma.fi.scheduler.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class Shift {
    @Id
    private Long id;

    private Integer weekNumber;

    private String dayOfWeek;

    private Integer number;

    @OneToOne
    private Lesson lesson;
}
