package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import subway.exception.ErrorMessage;
import subway.exception.GameException;

public class StationRepository {
    private static final List<Station> stations = new ArrayList<>();

    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static void addStation(final Station station) {
        if (stations.contains(station)) {
            throw new GameException(ErrorMessage.DUPLICATE_STATION);
        }

        stations.add(station);
    }

    public static boolean deleteStation(final Station deletedStation) {
        return stations.removeIf(station -> Objects.equals(station, deletedStation));
    }

    public static Station findByStationName(final String stationName) {
        return stations.stream()
                .filter(station -> station.isName(stationName))
                .findAny()
                .orElseThrow(() -> new GameException(ErrorMessage.INVALID_STATION));
    }
}
