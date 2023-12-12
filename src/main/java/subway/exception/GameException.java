package subway.exception;

public class GameException extends IllegalArgumentException {

    public GameException(final ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
