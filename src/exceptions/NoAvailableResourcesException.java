package exceptions;

public class NoAvailableResourcesException extends GameActionException {

    public NoAvailableResourcesException() {
    }

    public NoAvailableResourcesException(String message) {
        super(message);
    }

}
