package ukma.fi.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.entities.Subject;
import ukma.fi.scheduler.service.LessonService;
import ukma.fi.scheduler.service.SubjectService;

import javax.validation.Valid;

@RestController
@RequestMapping("subject")
public class SubjectController {

    @Autowired
    private SubjectService lessonService;

    @PostMapping("/add")
    public void addSubject(@Valid @RequestBody Subject newSubject) {

    }

    @GetMapping("/{id}")
    public Subject getSubject(@Valid @PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@Valid @PathVariable Long id) {
    }

    @PutMapping("/{id}")
    public void updateSubject(@Valid @RequestBody Subject newSubject, @PathVariable Long id) {

    }
}
