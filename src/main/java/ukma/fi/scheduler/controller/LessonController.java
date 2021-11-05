package ukma.fi.scheduler.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.exceptionHandlers.exceptions.LessonNotFoundException;
import ukma.fi.scheduler.service.LessonService;

import javax.validation.Valid;

@RestController
@RequestMapping("lesson")
@Validated
@Log4j2
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @PostMapping("/add")
    public void addLesson(@Valid @RequestBody Lesson newLesson) {

    }

    @GetMapping("/{id}")
    public Lesson getLesson(@Valid @PathVariable Long id) {
        throw new LessonNotFoundException(id);
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@Valid @PathVariable Long id) {
    }

    @PutMapping("/{id}")
    public void updateLesson(@Valid @RequestBody Lesson newLesson, @PathVariable Long id) {

    }


}
