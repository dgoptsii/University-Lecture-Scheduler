package ukma.fi.scheduler.controller.dto;

import lombok.Data;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;

import javax.validation.constraints.*;

@Data
public class LessonDTO {

    @NotBlank
    private Subject subject;

    @NotBlank
    private User teacher;

    @NotBlank
    @Min(1)
    @Max(6)
    private Integer dayOfWeek;

    @NotBlank
    @Min(1)
    @Max(7)
    private Integer lessonNumber;

    @NotBlank
    @Min(1)
    private Integer groupNumber;

}
