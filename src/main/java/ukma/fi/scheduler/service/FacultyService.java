package ukma.fi.scheduler.service;


import ukma.fi.scheduler.entities.*;

public interface FacultyService {

    void create(String name);

    void delete(Faculty faculty);

    void edit(Faculty faculty);

    void show(Long id);
}
