package subway.service;

import java.util.List;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.exception.ErrorMessage;
import subway.exception.GameException;

public class StationService {

    private StationService() {
    }

    public static void create(final String stationName) {
        StationRepository.addStation(new Station(stationName));
    }

    public static void delete(final String stationName) {
        if (isInLine(stationName)) {
            throw new GameException(ErrorMessage.LINE_EXISTENCE);
        }

        boolean success = StationRepository.deleteStationByName(stationName);

        if (!success) {
            throw new GameException(ErrorMessage.INVALID_STATION);
        }
    }

    private static boolean isInLine(String stationName) {
        List<Line> lines = LineRepository.lines();
        return lines.stream()
                .anyMatch(line -> line.containStation(stationName));
    }

    public static List<Station> readAll() {
        return StationRepository.stations();
    }
}
