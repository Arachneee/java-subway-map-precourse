package subway.controller;


import java.util.List;
import java.util.stream.Collectors;
import subway.InputRoofer;
import subway.domain.Station;
import subway.domain.StationLineFunction;
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
        StationLineFunction stationFunction = getStationFunction();

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

    private StationLineFunction getStationFunction() {
        outputView.printStationFunction();
        return InputRoofer.getByRoof(() -> {
            String stationFunctionSource = inputView.readFunction();
            return StationLineFunction.from(stationFunctionSource);
        });
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
        Station station = getDeleteStation();

        try {
            StationService.delete(station);
            outputView.printDeleteStation();
        } catch (IllegalArgumentException illegalArgumentException) {
            outputView.printError(illegalArgumentException.getMessage());
        }
    }

    private Station getDeleteStation() {
        return InputRoofer.getByRoof(() -> {
            String stationSource = inputView.readDeleteStation();
            return new Station(stationSource);
        });
    }

    private void read() {
        List<Station> stations = StationService.readAll();
        List<String> stationNames = createStationNames(stations);
        outputView.printStations(stationNames);
    }

    private static List<String > createStationNames(List<Station> stations) {
        return stations.stream()
                .map(Station::getName)
                .collect(Collectors.toList());
    }
}
