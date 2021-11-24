package ukma.fi.scheduler.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ukma.fi.scheduler.controller.dto.LessonDTO;
import ukma.fi.scheduler.entities.Lesson;
import ukma.fi.scheduler.service.LessonService;

import javax.validation.Valid;

@Controller
@RequestMapping("/teacher/lesson")
@Validated
@Log4j2
public class LessonControllerTeacher {

    @Autowired
    private LessonService lessonService;

    @PostMapping("/add")
    public void addLesson(@RequestBody LessonDTO newLesson) {
        lessonService.create(newLesson.getId(), newLesson.getGroupNumber(), newLesson.getLessonNumber(), newLesson.getDayOfWeek());
    }

    @GetMapping("/{id}")
    public String getLesson(@Valid @PathVariable Long id) {
        return lessonService.show(id).toString();
    }

    @DeleteMapping("/{id}")
    public String deleteLesson(@Valid @PathVariable Long id) {
        Lesson lesson = lessonService.show(id);
        if (lesson != null && lesson.getSubject() != null) {
            long subjectId = lesson.getSubject().getId();
            lessonService.delete(id);
            return "redirect:/subject/" + subjectId;
        } else return null;
    }

    @PutMapping("/{id}")
    public String updateLesson(@Valid @RequestBody Lesson newLesson, @PathVariable Long id) {
        newLesson.setId(id);
        Lesson lesson = lessonService.show(id);
        if (lesson != null && lesson.getSubject() != null) {
            long subjectId = lesson.getSubject().getId();
            lessonService.edit(newLesson);
            return "redirect:/subject/" + subjectId;
        } else return null;
    }

}
