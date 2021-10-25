package ukma.fi.scheduler.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ukma.fi.scheduler.entities.Faculty;
import ukma.fi.scheduler.exceptionHandlers.exceptions.FacultyNotFoundException;
import ukma.fi.scheduler.service.FacultyService;

import javax.validation.Valid;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @PostMapping("/add")
    public void addFaculty(@RequestBody Faculty faculty) {
        facultyService.create(faculty.getName());
    }

    @GetMapping("/{id}")
    public Faculty getFaculty(@PathVariable Long id) {
        return facultyService.show(id);
    }

    @DeleteMapping("/{id}")
    public String deleteFaculty(@PathVariable Long id) {
        if (facultyService.delete(id))
            return "Delete faculty with id = " + id;
        else
            return null;
    }

    @PutMapping("/{id}")
    public void updateFaculty(@Valid @RequestBody Faculty newFaculty, @PathVariable Long id) {
        newFaculty.setId(id);
        facultyService.edit(newFaculty);
    }
}
