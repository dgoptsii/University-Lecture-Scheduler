package ukma.fi.scheduler.controller.dto;

import lombok.*;
import ukma.fi.scheduler.entities.User;
import javax.validation.constraints.*;

@Data
public class SubjectLectureDTO {

    // Subject
    @NotBlank
    private String name;

    @NotBlank
    @Min(0)
    @Max(10)
    private Integer maxGroups;

    @NotBlank
    private String specialty;

    @NotBlank
    @Min(1)
    @Max(4)
    private Integer year;

    //Lecture
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

}
