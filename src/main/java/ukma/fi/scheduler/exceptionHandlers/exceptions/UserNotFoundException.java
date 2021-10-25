package ukma.fi.scheduler.exceptionHandlers.exceptions;

import ukma.fi.scheduler.entities.User;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id){
        super("User " + id + " not found ");
    }
}
