package subway.service;

import java.util.List;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.exception.ErrorMessage;
import subway.exception.GameException;

public class LineService {

    private LineService() {
    }

    public static void create(final String lineName, final String upStationName, final String downStationName) {
        Line line = new Line(lineName);
        Station upStation = StationRepository.findByStationName(upStationName);
        Station downStation = StationRepository.findByStationName(downStationName);
        line.create(upStation, downStation);

        LineRepository.addLine(line);
    }

    public static void delete(String lineName) {
        boolean success = LineRepository.deleteLineByName(lineName);

        if (!success) {
            throw new GameException(ErrorMessage.NONE_LINE);
        }
    }

    public static List<Line> readAll() {
        return LineRepository.lines();
    }
}
