package fontys.s3.Bussiness.Exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("User with the given email already exists.");
    }
}