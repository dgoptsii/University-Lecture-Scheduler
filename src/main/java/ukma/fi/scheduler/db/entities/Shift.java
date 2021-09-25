package ukma.fi.scheduler.db.entities;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
@Data
@DynamicUpdate
public class Shift {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer weekNumber;

    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
    private String dayOfWeek;
    private Integer number;


}
