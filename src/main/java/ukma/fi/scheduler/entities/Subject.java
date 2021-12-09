package ukma.fi.scheduler.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Min(0)
    private Integer maxGroups;

    @Column(nullable = false)
    @NotBlank
    private String speciality;

    @Column(nullable = false)
    @Min(1)
    @Max(4)
    private Integer year;

    public Subject(String name, Integer maxGroups, String specialty, Integer year) {
        this.name = name;
        this.maxGroups = maxGroups;
        this.speciality = specialty;
        this.year = year;
    }

}
