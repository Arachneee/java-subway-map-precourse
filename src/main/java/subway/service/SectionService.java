package subway.service;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Order;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.exception.ErrorMessage;
import subway.exception.GameException;

public class SectionService {

    public static void create(final String lineName, final String stationName, final int orderSource) {
        Line line = LineRepository.findByLineName(lineName);
        Station station = StationRepository.findByStationName(stationName);
        Order order = new Order(orderSource);

        if (line.containStation(station)) {
            throw new GameException(ErrorMessage.INVALID_SECTION);
        }

        if (line.canAddOrder(order)) {
            throw new GameException(ErrorMessage.INVALID_ORDER);
        }

    }
}
