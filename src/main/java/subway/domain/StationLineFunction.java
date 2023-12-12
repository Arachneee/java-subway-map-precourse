package subway.domain;

import java.util.Arrays;
import subway.exception.ErrorMessage;
import subway.exception.GameException;

public enum StationLineFunction {

    CREATE("1"),
    DELETE("2"),
    READ("3"),
    BACK("B");

    private final String value;

    StationLineFunction(String value) {
        this.value = value;
    }

    public static StationLineFunction from(final String value) {
        return Arrays.stream(values())
                .filter(function -> function.value.equals(value))
                .findAny()
                .orElseThrow(() -> new GameException(ErrorMessage.INVALID_FUNCTION));
    }

    public boolean isBack() {
        return this.equals(BACK);
    }

    public boolean isCreate() {
        return this.equals(CREATE);
    }

    public boolean isDelete() {
        return this.equals(DELETE);
    }

    public boolean isRead() {
        return this.equals(READ);
    }
}
