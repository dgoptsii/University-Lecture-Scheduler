package ukma.fi.scheduler.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import ukma.fi.scheduler.controller.dto.LessonDTO;

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

    @ManyToOne(cascade = CascadeType.ALL)
    private Subject subject;

    @ManyToOne
    private User teacher;

    @Column(nullable = false)
    @Min(1)
    @Max(6)
    private Integer dayOfWeek;

    @Column(nullable = false)
    @Min(1)
    @Max(7)
    private Integer lessonNumber;

    @Column(nullable = false)
    @Min(0)
    private Integer groupNumber;


    public Lesson(Subject subject, Integer dayOfWeek, Integer lessonNumber, User teacher, Integer groupNumber) {
        this.subject = subject;
        this.dayOfWeek = dayOfWeek;
        this.lessonNumber = lessonNumber;
        this.teacher = teacher;
        this.groupNumber = groupNumber;
    }

    public static Lesson createFromDto(LessonDTO dto) {
        Lesson res = new Lesson();
        res.subject = dto.getSubject();
        res.dayOfWeek = dto.getDayOfWeek();
        res.lessonNumber = dto.getLessonNumber();
        res.teacher = dto.getTeacher();
        res.groupNumber = dto.getGroupNumber();
        return res;
    }
}
