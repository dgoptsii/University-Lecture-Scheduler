package ukma.fi.scheduler.controller.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
