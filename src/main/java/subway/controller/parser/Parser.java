package subway.controller.parser;


import subway.exception.ErrorMessage;
import subway.exception.GameException;

public final class Parser {
    private Parser() {
    }

    public static int convertToInt(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException numberFormatException) {
            throw new GameException(ErrorMessage.NOT_NUMBER);
        }
    }
}
