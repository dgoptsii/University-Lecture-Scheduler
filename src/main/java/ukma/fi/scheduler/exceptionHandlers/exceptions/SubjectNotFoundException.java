package ukma.fi.scheduler.exceptionHandlers.exceptions;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException(Long id){
        super("Subject " + id + " not found ");
    }
}
