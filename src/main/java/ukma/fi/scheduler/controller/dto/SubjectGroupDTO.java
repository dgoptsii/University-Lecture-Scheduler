package ukma.fi.scheduler.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SubjectGroupDTO {
    @NotBlank
    String subName;

    @NotNull
    Long subId;

    @NotNull
    Integer groupNum;

    @NotNull
    @Min(0)
    Integer groupMax;

    @NotNull
    boolean normative;
}
