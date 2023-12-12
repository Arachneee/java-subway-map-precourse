package subway.domain;

import subway.exception.ErrorMessage;
import subway.exception.GameException;

public class Station {

    private static final int MIN_NAME_LENGTH = 2;
    private String name;

    public Station(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        if (name.length() < MIN_NAME_LENGTH) {
            throw new GameException(ErrorMessage.INVALID_SIZE);
        }
    }

    public String getName() {
        return name;
    }

    // 추가 기능 구현
}
