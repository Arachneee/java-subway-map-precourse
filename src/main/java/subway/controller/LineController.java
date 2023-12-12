package subway.controller;

import java.util.List;
import java.util.stream.Collectors;
import subway.InputRoofer;
import subway.domain.Line;
import subway.domain.Station;
import subway.domain.StationLineFunction;
import subway.response.StationDto;
import subway.service.LineService;
import subway.service.StationService;
import subway.view.InputView;
import subway.view.OutputView;

public class LineController {
    private final InputView inputView;
    private final OutputView outputView;

    public LineController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        StationLineFunction lineFunction = getLineFunction();

        if (lineFunction.isCreate()) {
            create();
            return;
        }

        if (lineFunction.isDelete()) {
            delete();
            return;
        }

        if (lineFunction.isRead()) {
            read();
        }

        // LineFunction is BACK
    }

    private StationLineFunction getLineFunction() {
        return InputRoofer.getByRoof(() -> {
            String functionSource = inputView.readStationLineFunction();
            return StationLineFunction.from(functionSource);
        });
    }

    private void create() {
        Line line = getCreateLine();
        Station upStation = getUpStation();
        Station downStation = getDownStation();

        try {
            LineService.create(line, upStation, downStation);
            outputView.printCreateLine();
        } catch (IllegalArgumentException illegalArgumentException) {
            outputView.printError(illegalArgumentException.getMessage());
        }
    }

    private Station getUpStation() {
        return InputRoofer.getByRoof(() -> {
            String stationSource = inputView.readUpStation();
            return new Station(stationSource);
        });
    }

    private Station getDownStation() {
        return InputRoofer.getByRoof(() -> {
            String stationSource = inputView.readDownStation();
            return new Station(stationSource);
        });
    }

    private Line getCreateLine() {
        return InputRoofer.getByRoof(() -> {
            String lineSource = inputView.readCreateLine();
            return new Line(lineSource);
        });
    }

    private void delete() {
        Line line = getDeleteLine();

        try {
            LineService.delete(line);
            outputView.printDeleteLine();
        } catch (IllegalArgumentException illegalArgumentException) {
            outputView.printError(illegalArgumentException.getMessage());
        }
    }

    private Line getDeleteLine() {
        return InputRoofer.getByRoof(() -> {
            String lineSource = inputView.readDeleteLine();
            return new Line(lineSource);
        });
    }

    private void read() {
        List<Station> stations = StationService.readAll();
        List<StationDto> stationDtos = createStationDtos(stations);
        outputView.printStations(stationDtos);
    }

    private static List<StationDto> createStationDtos(List<Station> stations) {
        return stations.stream()
                .map(station -> new StationDto(station.getName()))
                .collect(Collectors.toList());
    }
}
