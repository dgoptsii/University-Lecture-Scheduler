package ukma.fi.scheduler.controller.dto;

import lombok.Data;
import ukma.fi.scheduler.entities.User;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SubjectLectureDTO {

    // Subject
    @NotBlank
    private String name;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer maxGroups;

    @NotBlank
    private String specialty;

    @NotNull
    @Min(1)
    @Max(4)
    private Integer year;

    //Lecture
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
}
