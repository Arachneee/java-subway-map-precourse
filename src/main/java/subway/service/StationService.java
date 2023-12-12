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

    public static void create(final Station station) {
        StationRepository.addStation(station);
    }

    public static void delete(final Station station) {
        if (isInLine(station)) {
            throw new GameException(ErrorMessage.LINE_EXISTENCE);
        }

        boolean success = StationRepository.deleteStation(station);

        if (!success) {
            throw new GameException(ErrorMessage.INVALID_STATION);
        }
    }

    private static boolean isInLine(Station station) {
        List<Line> lines = LineRepository.lines();
        return lines.stream()
                .anyMatch(line -> line.containStation(station));
    }

    public static List<Station> readAll() {
        return StationRepository.stations();
    }
}
