package ukma.fi.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ukma.fi.scheduler.controller.dto.SubjectDTO;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.exceptionHandlers.exceptions.FacultyNotFoundException;
import ukma.fi.scheduler.exceptionHandlers.exceptions.SubjectNotFoundException;
import ukma.fi.scheduler.service.LessonService;
import ukma.fi.scheduler.service.SubjectService;

import javax.validation.Valid;

@RestController
@RequestMapping("subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/add")
    public void addSubject(@Valid @RequestBody SubjectDTO newSubject) {
        subjectService.create(newSubject);
    }

    @GetMapping("/{id}")
    public Subject getSubject(@Valid @PathVariable Long id) {
        return subjectService.show(id);
    }

    @DeleteMapping("/{id}")
    public String deleteSubject(@Valid @PathVariable Long id) {
        if (subjectService.delete(id))
            return "Delete subject with id = "+id;
        else
            return null;
    }

    @PutMapping("/{id}")
    public void updateSubject(@Valid @RequestBody Subject newSubject, @PathVariable Long id) {
        newSubject.setId(id);
        subjectService.edit(newSubject);
    }
}
