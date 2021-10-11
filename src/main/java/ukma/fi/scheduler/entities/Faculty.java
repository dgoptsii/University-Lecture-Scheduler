package ukma.fi.scheduler.entities;



import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Data
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique=true)
    private Long id;

    @Column(unique=true, nullable = false)
    private String name;
}
