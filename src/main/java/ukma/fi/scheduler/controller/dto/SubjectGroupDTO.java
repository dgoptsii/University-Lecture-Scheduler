package ukma.fi.scheduler.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class SubjectGroupDTO {
    @NotBlank
    String subName;

    @NotNull
    Long subId;

    @NotNull
    @Min(1)
    Integer groupNum;

    @NotNull
    @Min(0)
    Integer groupMax;

    @NotNull
    boolean normative;
}
