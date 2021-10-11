package ukma.fi.scheduler.service.impl;

import com.sun.media.sound.InvalidDataException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.entities.*;
import ukma.fi.scheduler.repository.FacultyRepository;
import ukma.fi.scheduler.repository.SubjectRepository;
import ukma.fi.scheduler.service.*;

import javax.persistence.OneToOne;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public Subject create(String name, Long facultyId,String normative) {
        if(!facultyRepository.findById(facultyId).isPresent()){
            try {
                throw new InvalidDataException("Faculty id is incorrect");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        checkParams(name,normative);
        Subject toSave = new Subject(name,facultyRepository.findById(facultyId).get(),normative);
        return subjectRepository.save(toSave);
    }

    @Override
    public Subject edit(Subject subject) {
        if (!subjectRepository.findById(subject.getId()).isPresent()) {
            try {
                throw new NotFoundException("Subject not found.");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        checkParams(subject.getName(),subject.getNormative());
        return subjectRepository.save(subject);
    }

    @Override
    public boolean delete(Long id) {
        if (subjectRepository.findById(id).isPresent()) {
            subjectRepository.deleteById(id);
        } else {
            try {
                throw new NotFoundException("Subject not found.");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            return false;
        }
        return !subjectRepository.findById(id).isPresent();
    }

    @Override
    public Subject show(Long id) {
        if (!subjectRepository.findById(id).isPresent()) {
            try {
                throw new NotFoundException("Subject not found.");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return subjectRepository.findById(id).get();
    }

    @Override
    public List<Subject> findByFaculty(Long id) {
        if(!facultyRepository.findById(id).isPresent()){
            try {
                throw new InvalidDataException("Faculty id is incorrect");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        return  subjectRepository.findByFaculty_Id(id);
    }

    private void checkParams(String name,String normative) {
        if (subjectRepository.findByName(name).isPresent()) {
            try {
                throw new InvalidDataException("Subject with this name is already exist.");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        if (!normative.equals("Y") && !normative.equals("N")) {
            try {
                throw new InvalidDataException("Invalid status.");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
    }
}
