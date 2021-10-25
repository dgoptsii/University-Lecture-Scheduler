package ukma.fi.scheduler.exceptionHandlers.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id){
        super("User with id: " + id + " not found ");
    }

    public UserNotFoundException(String login){
        super("User with login: " + login + " not found ");
    }
}
