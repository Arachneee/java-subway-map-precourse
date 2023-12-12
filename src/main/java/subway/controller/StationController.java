package subway.controller;


import java.util.List;
import java.util.stream.Collectors;
import subway.InputRoofer;
import subway.domain.Station;
import subway.domain.StationFunction;
import subway.response.StationDto;
import subway.service.StationService;
import subway.view.InputView;
import subway.view.OutputView;

public class StationController {

    private final InputView inputView;
    private final OutputView outputView;

    public StationController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        StationFunction stationFunction = getStationFunction();

        if (stationFunction.isCreate()) {
            create();
            return;
        }

        if (stationFunction.isDelete()) {
            delete();
            return;
        }

        if (stationFunction.isRead()) {
            read();
        }

        // StationFunction is BACK
    }

    private void create() {
        Station station = getStation();

        try {
            StationService.create(station);
            outputView.printCreateStation();
        } catch (IllegalArgumentException illegalArgumentException) {
            outputView.printError(illegalArgumentException.getMessage());
        }
    }

    private Station getStation() {
        return InputRoofer.getByRoof(() -> {
            String stationSource = inputView.readStation();
            return new Station(stationSource);
        });
    }

    private void delete() {
        Station station = getStation();

        try {
            StationService.delete(station);
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

    private StationFunction getStationFunction() {
        return InputRoofer.getByRoof(() -> {
            String stationFunctionSource = inputView.readStationFunction();
            return StationFunction.from(stationFunctionSource);
        });
    }
}
