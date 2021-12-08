package ukma.fi.scheduler.exceptionHandlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ukma.fi.scheduler.exceptionHandlers.exceptions.*;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    private ModelAndView getErrorMav(HttpServletRequest req, Exception exception, Integer status) throws Exception {
        // Rethrow annotated exceptions or they will be processed here instead.
        if (AnnotationUtils.findAnnotation(exception.getClass(),
                ResponseStatus.class) != null)
            throw exception;

        log.error("Request: " + req.getRequestURI() + " raised " + exception);

        ModelAndView mav = new ModelAndView("error");
        mav.addObject("exception", exception);
        mav.addObject("message", exception.getMessage());
        mav.addObject("status", status);

        return mav;
    }

    @ExceptionHandler(AccessException.class)
    public ModelAndView accessError(HttpServletRequest req, Exception exception) throws Exception {
        return getErrorMav(req,exception,403);
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(FacultyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String facultyNotFoundHandler(FacultyNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SubjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String subjectNotFoundHandler(SubjectNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(InvalidData.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView invalidDataHandler(HttpServletRequest req, InvalidData exception) throws Exception {
        return getErrorMav(req,exception,403);
    }


    @ResponseBody
    @ExceptionHandler(LessonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String lessonNotFoundHandler(LessonNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception exception)
            throws Exception {
        return getErrorMav(req,exception,500);
    }
}
