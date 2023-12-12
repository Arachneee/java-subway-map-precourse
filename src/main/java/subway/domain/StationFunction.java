package subway.domain;

import java.util.Arrays;
import subway.exception.ErrorMessage;
import subway.exception.GameException;

public enum StationFunction {

    CREATE("1"),
    DELETE("2"),
    READ("3"),
    BACK("B");

    private final String value;

    StationFunction(String value) {
        this.value = value;
    }

    public static StationFunction from(final String value) {
        return Arrays.stream(values())
                .filter(function -> function.value.equals(value))
                .findAny()
                .orElseThrow(() -> new GameException(ErrorMessage.INVALID_FUNCTION));
    }
}
