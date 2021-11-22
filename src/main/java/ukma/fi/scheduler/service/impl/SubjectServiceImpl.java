package ukma.fi.scheduler.service.impl;

import com.sun.media.sound.InvalidDataException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.ServiceMarker;
import ukma.fi.scheduler.controller.dto.SubjectDTO;
import ukma.fi.scheduler.entities.*;
import ukma.fi.scheduler.exceptionHandlers.exceptions.InvalidData;

import ukma.fi.scheduler.exceptionHandlers.exceptions.SubjectNotFoundException;
import ukma.fi.scheduler.repository.FacultyRepository;
import ukma.fi.scheduler.repository.SubjectRepository;
import ukma.fi.scheduler.service.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@ServiceMarker
@Service
@Log4j2
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public Subject create(@Valid SubjectDTO subjectDTO) {
        if(!facultyRepository.findById(subjectDTO.getFacultyId()).isPresent()){
            throw new InvalidData(Collections.singletonMap("facultyId",subjectDTO.getFacultyId().toString()));
        }
        checkParams(subjectDTO.getName(),subjectDTO.getNormative(),new HashMap<>());
        Subject toSave = new Subject(subjectDTO.getName(),facultyRepository.findById(subjectDTO.getFacultyId()).get(),subjectDTO.getNormative());
        log.info("created subject -> name:" + subjectDTO.getName());
        return subjectRepository.save(toSave);
    }

    @Override
    public Subject edit(Subject subject) {
        if (!subjectRepository.findById(subject.getId()).isPresent()) {
            throw new SubjectNotFoundException(subject.getId());
        }
        checkParams(subject.getName(),subject.getNormative(), new HashMap<>());
        log.info("edit subject -> subject id:" + subject.getId());
        return subjectRepository.save(subject);
    }

    @Override
    public boolean delete(Long id) {
        if (subjectRepository.findById(id).isPresent()) {
            subjectRepository.deleteById(id);
        } else {
            throw new SubjectNotFoundException(id);
        }
        log.info("delete shift -> id:" + id);
        return !subjectRepository.findById(id).isPresent();
    }

    @Override
    public Subject show(Long id) {
        if (!subjectRepository.findById(id).isPresent()) {
            throw new SubjectNotFoundException(id);
        }
        log.info("show shift -> id:" + id);
        return subjectRepository.findById(id).get();
    }

    @Override
    public List<Subject> findByFaculty(Long id) {
        if(!facultyRepository.findById(id).isPresent()){
            throw new InvalidData(Collections.singletonMap("facultyId",id.toString()));
        }
        log.info("found by id -> faculty id:" + id);
        return  subjectRepository.findByFaculty_Id(id);
    }

    private void checkParams(String name, String normative, HashMap<String,String> invalid ) {
        if (subjectRepository.findByName(name).isPresent()) {
            try {
                throw new InvalidDataException("Subject with this name is already exist.");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        if (!normative.equals("Y") && !normative.equals("N")) {
            invalid.put("status",normative);
            throw new InvalidData(invalid);
        }
        log.info("check params -> name:" + name + " "+"normative:"+normative);
    }

    @Override
    public List<Subject> showAll(){
        return subjectRepository.findAll();
    }
}
