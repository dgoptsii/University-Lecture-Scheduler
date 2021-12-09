package ukma.fi.scheduler.service;

import ukma.fi.scheduler.controller.dto.SubjectLectureDTO;
import ukma.fi.scheduler.entities.Subject;

import java.util.List;

public interface SubjectService {

    Subject findSubjectById(Long id);

    Subject findSubjectByName(String name);

    List<Subject> findSubjectByIdIn(List<Long> id);

    Subject create(SubjectLectureDTO dto) throws Exception;

    List<Subject> findAll();

    void deleteSubject(Long id) throws Exception;

    boolean edit(Long id, Subject newSub) throws Exception;
}
