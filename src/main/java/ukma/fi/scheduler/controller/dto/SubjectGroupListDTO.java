package ukma.fi.scheduler.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode
public class SubjectGroupListDTO {

    private List<SubjectGroupDTO> chooseSubGroup;

    // default and parameterized constructor

    public void addAllDto(List<SubjectGroupDTO> dto) {
        this.chooseSubGroup.addAll(dto);
    }

}
