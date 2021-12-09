package ukma.fi.scheduler.exceptionHandlers.exceptions;

public class UserExistsException extends RuntimeException {
    public UserExistsException() {
        super("User with this login already exists");
    }
}
