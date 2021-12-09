package ukma.fi.scheduler.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonDTO {

    @NotNull
    private Subject subject;

    @NotNull
    private User teacher;

    @NotNull
    @Min(1)
    @Max(6)
    private Integer dayOfWeek;

    @NotNull
    @Min(1)
    @Max(7)
    private Integer lessonNumber;

    @NotNull
    @Min(1)
    private Integer groupNumber;

}
