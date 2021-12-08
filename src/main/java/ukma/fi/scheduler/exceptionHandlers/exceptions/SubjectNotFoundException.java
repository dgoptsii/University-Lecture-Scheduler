package ukma.fi.scheduler.exceptionHandlers.exceptions;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException(String id) {
        super("Subject " + id + " not found ");
    }
}
