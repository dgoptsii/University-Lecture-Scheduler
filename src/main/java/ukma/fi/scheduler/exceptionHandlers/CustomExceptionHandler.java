package ukma.fi.scheduler.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ukma.fi.scheduler.exceptionHandlers.exceptions.*;
@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFoundHandler(UserNotFoundException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(FacultyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String facultyNotFoundHandler(FacultyNotFoundException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SubjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String subjectNotFoundHandler(SubjectNotFoundException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(InvalidData.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String invalidDataHandler(InvalidData ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(LessonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String lessonNotFoundHandler(LessonNotFoundException ex){
        return ex.getMessage();
    }
}
