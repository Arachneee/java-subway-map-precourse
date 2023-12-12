package subway.domain;

import java.util.Arrays;
import subway.exception.ErrorMessage;
import subway.exception.GameException;

public enum SectionFunction {

    CREATE("1"),
    DELETE("2"),
    BACK("B");

    private final String value;

    SectionFunction(String value) {
        this.value = value;
    }

    public static SectionFunction from(final String value) {
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
}
