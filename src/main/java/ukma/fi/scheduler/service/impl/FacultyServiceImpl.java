package ukma.fi.scheduler.service.impl;

import com.sun.media.sound.InvalidDataException;
import com.sun.org.apache.bcel.internal.generic.FCMPG;
import javassist.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import ukma.fi.scheduler.ServiceMarker;
import ukma.fi.scheduler.entities.*;
import ukma.fi.scheduler.exceptionHandlers.exceptions.FacultyNotFoundException;
import ukma.fi.scheduler.repository.FacultyRepository;
import ukma.fi.scheduler.service.*;
import org.springframework.stereotype.Service;

import java.util.List;


@ServiceMarker
@Service
@Log4j2
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;




    @Override
    public Faculty create(String name) {
        checkParams(name);
        log.info("created faculty -> name:" + name);
        return facultyRepository.save(new Faculty(name));
    }

    @Override
    public boolean delete(Long id) {
        if (facultyRepository.findById(id).isPresent()) {
            facultyRepository.deleteById(id);
        } else {
            try {
                throw new NotFoundException("Faculty not found.");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            return false;
        }
        log.info("deleted faculty -> id:" + id);
        return !facultyRepository.findById(id).isPresent();
    }

    @Override
    public Faculty edit(Faculty faculty) {
        if (!facultyRepository.findById(faculty.getId()).isPresent()) {
            throw new FacultyNotFoundException(faculty.getId());

        }
        checkParams(faculty.getName());
        log.info("edit faculty -> id:" + faculty.getId());
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty show(Long facultyId) {
        if (!facultyRepository.findById(facultyId).isPresent()) {
            try {
                throw new NotFoundException("Faculty not found.");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        log.info("show faculty -> id:" + facultyId);
        return facultyRepository.findById(facultyId).get();
    }

    @Override
    public List<Faculty> showAll() {
        return facultyRepository.findAll();
    }

    private void checkParams(String name) {
        if (facultyRepository.findByName(name).isPresent()) {
            try {
                throw new InvalidDataException("Faculty with this name is already exist.");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        log.info("check params -> name:" + name);
    }
}
