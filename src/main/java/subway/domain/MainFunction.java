package subway.domain;

import java.util.Arrays;
import subway.exception.ErrorMessage;
import subway.exception.GameException;

public enum MainFunction {
    STATION_MANAGEMENT("1"),
    LINE_MANAGEMENT("2"),
    SECTION_MANAGEMENT("3"),
    SUBWAY_ROUTE_MAP("4"),
    TERMINATION("Q");

    private final String value;

    MainFunction(String value) {
        this.value = value;
    }

    public static MainFunction from(final String value) {
        return Arrays.stream(values())
                .filter(function -> function.value.equals(value))
                .findAny()
                .orElseThrow(() -> new GameException(ErrorMessage.INVALID_FUNCTION));
    }

    public boolean isTerminate() {
        return this.equals(TERMINATION);
    }

    public boolean isStation() {
        return this.equals(STATION_MANAGEMENT);
    }
}
