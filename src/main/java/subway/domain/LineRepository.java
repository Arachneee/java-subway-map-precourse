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
        if (isContainLine(line)) {
            throw new GameException(ErrorMessage.DUPLICATE_LINE);
        }

        lines.add(line);
    }

    private static boolean isContainLine(final Line addLine) {
        return lines.stream()
                .anyMatch(line -> line.getName().equals(addLine.getName()));
    }

    public static boolean deleteLineByName(final String name) {
        return lines.removeIf(line -> Objects.equals(line.getName(), name));
    }

    public static Line findByLineName(final String lineName) {
        return lines.stream()
                .filter(line -> line.isName(lineName))
                .findAny()
                .orElseThrow(() -> new GameException(ErrorMessage.NONE_LINE));
    }
}
