package ukma.fi.scheduler.db.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String login;
    private String password;
    private Boolean admin;




}
