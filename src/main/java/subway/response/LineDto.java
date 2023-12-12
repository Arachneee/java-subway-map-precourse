package subway.response;

import java.util.List;
import subway.domain.Line;

public class LineDto {

    private final String name;
    private final List<String> stations;

    private LineDto(String name, List<String> stations) {
        this.name = name;
        this.stations = stations;
    }

    public static LineDto from(final Line line) {
        return new LineDto(line.getName(), line.getStationNames());
    }

    public String getName() {
        return name;
    }

    public List<String> getStations() {
        return stations;
    }
}
