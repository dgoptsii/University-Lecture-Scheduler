package ukma.fi.scheduler.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class SubjectGroupListDTO {

    private List<SubjectGroupDTO> chooseSubGroup = new ArrayList<>();

    // default and parameterized constructor
    public void addAllDto(List<SubjectGroupDTO> dto) {
        this.chooseSubGroup.addAll(dto);
    }

    public int size(){
        return chooseSubGroup.size();
    }
}
