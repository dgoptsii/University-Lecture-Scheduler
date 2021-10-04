package ukma.fi.scheduler.service;
import ukma.fi.scheduler.entities.*;

public interface SubjectService {
    void create(Subject subject);

    void edit(Subject subject);

    void delete(Subject subject);

    void show(Long id);
}
