package subway.service;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;

public class LineService {

    private LineService() {
    }


    public static void create(final Line line, final Station upStation, final Station downStation) {
        line.create(upStation, downStation);
        LineRepository.addLine(line);
    }

    public static void delete(Line line) {

    }
}
