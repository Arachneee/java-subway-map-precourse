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
        Line line = getLine();

        try {
            LineService.create(line);
            outputView.printCreateStation();
        } catch (IllegalArgumentException illegalArgumentException) {
            outputView.printError(illegalArgumentException.getMessage());
        }
    }

    private Line getLine() {
        return InputRoofer.getByRoof(() -> {
            String lineSource = inputView.readLine();
            return new Line(lineSource);
        });
    }

    private void delete() {
        Line line = getLine();

        try {
            LineService.delete(line);
            outputView.printDeleteStation();
        } catch (IllegalArgumentException illegalArgumentException) {
            outputView.printError(illegalArgumentException.getMessage());
        }
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
