package ukma.fi.scheduler.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Data
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Min(1)
    @Max(7)
    @Column(nullable = false)
    private String dayOfWeek;

    @Min(1)
    @Max(7)
    @Column(nullable = false)
    private Integer lessonNumber;

    @Min(1)
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

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", lessonNumber=" + lessonNumber +
                ", groupNumber=" + groupNumber +
                ", subject=" + subject.getName() +
                '}';
    }
}
