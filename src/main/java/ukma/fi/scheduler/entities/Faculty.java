package ukma.fi.scheduler.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true, nullable = false)
    private String name;

    public Faculty(String name) {
        this.name = name;
    }

    public Faculty(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
