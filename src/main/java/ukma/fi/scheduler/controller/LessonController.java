package ukma.fi.scheduler.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.service.LessonService;

import javax.validation.Valid;

@Controller
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
    public String getLesson(@Valid @PathVariable Long id) {
        return  lessonService.show(id).toString();
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@Valid @PathVariable Long id) {
    }

    @PutMapping("/{id}")
    public void updateLesson(@Valid @RequestBody Lesson newLesson, @PathVariable Long id) {

    }

}
