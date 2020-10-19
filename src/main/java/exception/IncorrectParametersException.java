package exception;

/**
 * Thrown when a user enter a wrong input. These include:
 * Song validation
 * Performer validation
 * AudioPlayer validation
 */
public class IncorrectParametersException extends RuntimeException {
    public IncorrectParametersException(String message) {
        super(message);
    }
}
