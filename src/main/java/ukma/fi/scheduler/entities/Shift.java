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

    private Integer weekNumber = null;

    private String dayOfWeek  = null;

    private Integer number  = null;

    private String isCancel = "N";

    @OneToOne(optional = false)
    private Lesson lesson;

    public Shift createCancel(Lesson lesson,Integer weekNumber){
        Shift cancel = new Shift();
        cancel.setLesson(lesson);
        cancel.setWeekNumber(weekNumber);
        cancel.setIsCancel("Y");
        return cancel;
    }
    public Shift createShift(Integer weekNumber, String dayOfWeek, Integer number, Lesson lesson){
        Shift cancel = new Shift();
        cancel.setLesson(lesson);
        cancel.setWeekNumber(weekNumber);
        cancel.setDayOfWeek(dayOfWeek);
        cancel.setNumber(number);
        return cancel;
    }
}
