package ukma.fi.scheduler.exceptionHandlers.exceptions;

public class LessonDeleteException extends RuntimeException {

    public LessonDeleteException() {
        super("You cannot delete lectures!");
    }

}
