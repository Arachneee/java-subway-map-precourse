package subway.domain;

import subway.exception.ErrorMessage;
import subway.exception.GameException;

public class Order {

    private static final int MIN_ORDER = 1;
    private final int value;

    public Order(int value) {
        if (value < MIN_ORDER) {
            throw new GameException(ErrorMessage.INVALID_ORDER);
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
