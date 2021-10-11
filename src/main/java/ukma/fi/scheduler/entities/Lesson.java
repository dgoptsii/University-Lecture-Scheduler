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

    private Integer weekStart;
    private Integer weekEnd;

    //lection or practice
    private String type;

    private Integer lessonNumber;

    // or maybe if groupNumber = 0 then it is lection
    private Integer groupNumber;

    @ManyToOne
    private Subject subject;
}
