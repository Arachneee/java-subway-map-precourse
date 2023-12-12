package subway.service;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Order;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.exception.ErrorMessage;
import subway.exception.GameException;

public class SectionService {

    private static final int MIN_STATION_COUNT = 3;
    public static void create(final String lineName, final String stationName, final int orderSource) {
        Line line = LineRepository.findByLineName(lineName);
        Station station = StationRepository.findByStationName(stationName);
        Order order = new Order(orderSource);

        line.addSection(station, order);
    }

    public static void delete(final String lineName, String stationName) {
        Line line = LineRepository.findByLineName(lineName);
        Station station = StationRepository.findByStationName(stationName);

        if (line.totalStationCount() < MIN_STATION_COUNT) {
            throw new GameException(ErrorMessage.INVALID_STATION_COUNT);
        }

        boolean success = line.deleteSection(station);

        if (!success) {
            throw new GameException(ErrorMessage.NONE_SECTION);
        }
    }
}
