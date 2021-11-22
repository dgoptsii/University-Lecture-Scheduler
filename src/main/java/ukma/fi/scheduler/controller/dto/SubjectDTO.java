package ukma.fi.scheduler.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SubjectDTO {

    @NotNull
    private String name;

    @NotNull
    @Min(1)
    private Long facultyId;

    @NotNull
    private String normative;
}
