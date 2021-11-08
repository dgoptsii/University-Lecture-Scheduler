package ukma.fi.scheduler.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ukma.fi.scheduler.entities.Faculty;
import ukma.fi.scheduler.exceptionHandlers.exceptions.FacultyNotFoundException;
import ukma.fi.scheduler.service.FacultyService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("faculty")
@Validated
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @PostMapping("/add")
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.create(faculty.getName());
    }

    @GetMapping("/all")
    public List<Faculty> getFaculty() {
        return facultyService.showAll();
    }

    @Operation(summary = "Gets faculty")
    @GetMapping("/{id}")
    public Faculty getFaculty(@PathVariable Long id) {
        return facultyService.show(id);
    }

    @DeleteMapping("/{id}")
    public String deleteFaculty(@PathVariable Long id) {
        return facultyService.delete(id) ? "Delete faculty with id = " + id : null;
    }

    @PutMapping("/{id}")
    public Faculty updateFaculty(@Valid @RequestBody Faculty newFaculty, @PathVariable Long id) {
        newFaculty.setId(id);
        facultyService.edit(newFaculty);
        return newFaculty;
    }



}
