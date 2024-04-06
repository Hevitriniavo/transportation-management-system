package hei.shool.transportationmanagementsystem.exceptions;

public class EntityUnauthorizedException extends RuntimeException {

    public EntityUnauthorizedException(String message) {
        super(message);
    }

    public EntityUnauthorizedException(Throwable cause) {
        super(cause);
    }
}
