package subway.service;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Order;
import subway.domain.Station;
import subway.domain.StationRepository;

public class SectionService {

    public static void create(final String lineName, final String stationName, final int orderSource) {
        Line line = LineRepository.findByLineName(lineName);
        Station station = StationRepository.findByStationName(stationName);
        Order order = new Order(orderSource);

        line.addSection(station, order);
    }

    public static void delete(final String lineName, String stationName) {
        Line line = LineRepository.findByLineName(lineName);
        Station station = StationRepository.findByStationName(stationName);

        boolean success = line.deleteSection(station);
        
    }
}
