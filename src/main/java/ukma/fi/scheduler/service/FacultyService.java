package ukma.fi.scheduler.service;


import ukma.fi.scheduler.entities.Faculty;

import java.util.List;

public interface FacultyService {

    Faculty create(String name);

    boolean delete(Long id);

    Faculty edit(Faculty faculty);

    Faculty show(Long id);

    List<Faculty> showAll();
}
