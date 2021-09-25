package ukma.fi.scheduler.service.interf;

import ukma.fi.scheduler.db.entities.Subject;

public interface SubjectServiceInterface {
    void create(Subject subject);

    void edit(Subject subject);

    void delete(Subject subject);
}
