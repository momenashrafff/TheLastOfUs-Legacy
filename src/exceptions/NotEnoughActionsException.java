package exceptions;

public class NotEnoughActionsException extends GameActionException {

    public NotEnoughActionsException() {
    }

    public NotEnoughActionsException(String message) {
        super(message);
    }

}
