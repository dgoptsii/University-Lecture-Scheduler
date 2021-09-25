package ukma.fi.scheduler.db.entities;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
@Data
public class Lesson {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn()
    private Subject subject;

    private String dayOfWeek;

    private Integer lessonNumber;

    private Integer groupNumber;

}
