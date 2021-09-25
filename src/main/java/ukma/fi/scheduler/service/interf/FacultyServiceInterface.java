package ukma.fi.scheduler.service.interf;

import ukma.fi.scheduler.db.entities.Faculty;
import ukma.fi.scheduler.db.entities.Lesson;
import ukma.fi.scheduler.db.entities.Subject;

public interface FacultyServiceInterface {

    void create(String name);

    void delete(Faculty faculty);

    void edit(Faculty faculty);
}
