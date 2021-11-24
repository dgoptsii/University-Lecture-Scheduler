package ukma.fi.scheduler.controller.dto;

import lombok.Data;

@Data
public class LessonDTO {
    private Long id;

    private String dayOfWeek;

    private Integer lessonNumber;

    private Integer groupNumber;

    private Long subjectId;
}
