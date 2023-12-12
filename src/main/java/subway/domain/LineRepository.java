package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import subway.exception.ErrorMessage;
import subway.exception.GameException;

public class LineRepository {
    private static final List<Line> lines = new ArrayList<>();

    public static List<Line> lines() {
        return Collections.unmodifiableList(lines);
    }

    public static void addLine(Line line) {
        if (lines.contains(line)) {
            throw new GameException(ErrorMessage.DUPLICATE_LINE);
        }

        lines.add(line);
    }

    public static boolean deleteLine(final Line deletedLine) {
        return lines.removeIf(line -> Objects.equals(line, deletedLine));
    }

    public static Line findByLineName(final String lineName) {
        return lines.stream()
                .filter(line -> line.isName(lineName))
                .findAny()
                .orElseThrow(() -> new GameException(ErrorMessage.NONE_LINE));
    }
}
