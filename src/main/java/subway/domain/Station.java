package subway.domain;

import java.util.Objects;
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

    public boolean isName(String stationName) {
        return name.equals(stationName);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Station station = (Station) o;
        return Objects.equals(name, station.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
