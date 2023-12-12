package subway.domain;

import java.util.ArrayList;
import java.util.List;
import subway.exception.ErrorMessage;
import subway.exception.GameException;

public class Line {

    private static final int MIN_NAME_LENGTH = 2;
    private String name;
    private List<Station> stations = new ArrayList<>();

    public Line(String name) {
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

    public boolean containStation(final Station station) {
        return stations.contains(station);
    }
}
