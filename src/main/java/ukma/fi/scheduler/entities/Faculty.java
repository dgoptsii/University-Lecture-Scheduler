package ukma.fi.scheduler.entities;



import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Faculty {
    @Id
    private Long id;

    private String name;
}
