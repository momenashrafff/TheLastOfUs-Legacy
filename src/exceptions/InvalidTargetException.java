package exceptions;

public class InvalidTargetException extends GameActionException {

    public InvalidTargetException() {
    }

    public InvalidTargetException(String message) {
        super(message);
    }

}
