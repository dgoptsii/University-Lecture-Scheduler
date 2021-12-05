package ukma.fi.scheduler.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.entities.User;
import ukma.fi.scheduler.repository.SubjectRepository;
import ukma.fi.scheduler.service.SubjectService;

@Service
@Log4j2
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject findSubjectById(Long id) {
        if (subjectRepository.findById(id).isPresent()) {
            log.info("found by id  -> id:" + id);
            return subjectRepository.findById(id).get();
        } else {
            return null;
        }
    }

}
