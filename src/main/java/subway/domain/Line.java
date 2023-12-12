package subway.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import subway.exception.ErrorMessage;
import subway.exception.GameException;

public class Line {

    private static final int MIN_NAME_LENGTH = 2;
    private String name;
    private List<Station> stations = new LinkedList<>();

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

    public void create(final Station upStation, final Station downStation) {
        if (upStation.equals(downStation)) {
            throw new GameException(ErrorMessage.DUPLICATE_STATION);
        }

        stations.add(upStation);
        stations.add(downStation);
    }

    public boolean isName(final String lineName) {
        return name.equals(lineName);
    }

    public void addSection(final Station station, final Order order) {
        if (containStation(station)) {
            throw new GameException(ErrorMessage.INVALID_SECTION);
        }

        if (order.getValue() < stations.size()) {
            throw new GameException(ErrorMessage.INVALID_ORDER);
        }

        stations.add(order.getValue(), station);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Line line = (Line) o;
        return Objects.equals(name, line.name) && Objects.equals(stations, line.stations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, stations);
    }
}
