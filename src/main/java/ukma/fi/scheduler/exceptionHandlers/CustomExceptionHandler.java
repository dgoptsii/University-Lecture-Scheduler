package ukma.fi.scheduler.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ukma.fi.scheduler.exceptionHandlers.exceptions.LessonNotFoundException;
import ukma.fi.scheduler.exceptionHandlers.exceptions.UserNotFoundException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundHandler(UserNotFoundException ex){
        return ex.getMessage();
    }


    @ResponseBody
    @ExceptionHandler(LessonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundHandler(LessonNotFoundException ex){
        return ex.getMessage();
    }
}
