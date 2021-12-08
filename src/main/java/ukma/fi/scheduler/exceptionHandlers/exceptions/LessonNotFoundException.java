package ukma.fi.scheduler.exceptionHandlers.exceptions;

public class LessonNotFoundException extends RuntimeException {

    public LessonNotFoundException(Long id) {
        super("Could not find lesson " + id);
    }

}
