package subway.service;

import java.util.List;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.exception.ErrorMessage;
import subway.exception.GameException;

public class LineService {

    private LineService() {
    }


    public static void create(final Line line, final Station upStation, final Station downStation) {
        line.create(upStation, downStation);
        LineRepository.addLine(line);
    }

    public static void delete(Line line) {
        boolean success = LineRepository.deleteLine(line);

        if (!success) {
            throw new GameException(ErrorMessage.NONE_LINE);
        }
    }

    public static List<Line> readAll() {
        return LineRepository.lines();
    }
}
