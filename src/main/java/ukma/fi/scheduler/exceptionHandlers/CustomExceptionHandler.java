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

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView HandleUserNotFoundException(HttpServletRequest req, UserNotFoundException exception) throws Exception {
        return getErrorMav(req,exception,404);
    }

    @ExceptionHandler(LessonNotFoundException.class)
    public ModelAndView HandleLessonNotFoundException(HttpServletRequest req, LessonNotFoundException exception) throws Exception {
        return getErrorMav(req,exception,404);
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    public ModelAndView HandleUserNotFoundException(HttpServletRequest req, SubjectNotFoundException exception) throws Exception {
        return getErrorMav(req,exception,404);
    }

    @ExceptionHandler(InvalidData.class)
    public ModelAndView HandleInvalidData(HttpServletRequest req, SubjectNotFoundException exception) throws Exception {
        return getErrorMav(req,exception,403);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest req, Exception exception)
            throws Exception {
        return getErrorMav(req,exception,500);
    }

    @ExceptionHandler(UserExistsException.class)
    public ModelAndView handleUserExistsException(HttpServletRequest req, UserExistsException exception)
            throws Exception {
        return getErrorMav(req,exception,409); //Conflict error
    }
}
