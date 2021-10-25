package ukma.fi.scheduler.service;
import ukma.fi.scheduler.controller.dto.SubjectDTO;
import ukma.fi.scheduler.entities.*;

import java.util.List;

public interface SubjectService {
    Subject create(SubjectDTO subjectDTO);

    Subject edit(Subject subject);

    boolean delete(Long id);

    Subject show(Long id);

    List<Subject> findByFaculty(Long id);
}
