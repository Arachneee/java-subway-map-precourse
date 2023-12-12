package subway.service;

import subway.domain.Line;
import subway.domain.LineRepository;

public class LineService {

    private LineService() {
    }


    public static void create(final Line line) {
        LineRepository.addLine(line);
    }

    public static void delete(Line line) {

    }
}
