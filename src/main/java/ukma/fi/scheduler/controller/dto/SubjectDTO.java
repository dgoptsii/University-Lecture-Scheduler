package ukma.fi.scheduler.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ukma.fi.scheduler.entities.Faculty;
import ukma.fi.scheduler.entities.Lesson;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SubjectDTO {

    @NonNull
    private String name;

    @NotNull
    @Min(1)
    private Long facultyId;

    @NotNull
    private String normative;
}
