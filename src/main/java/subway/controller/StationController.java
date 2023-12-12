package subway.controller;


import java.util.List;
import java.util.stream.Collectors;
import subway.util.InputRoofer;
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
        InputRoofer.runRoof(() -> {
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
        });
    }

    private StationLineFunction getStationFunction() {
        outputView.printStationFunction();
        return InputRoofer.getByRoof(() -> {
            String stationFunctionSource = inputView.readFunction();
            return StationLineFunction.from(stationFunctionSource);
        });
    }

    private void create() {
        String station = inputView.readCreateStation();
        StationService.create(station);
        outputView.printCreateStation();
    }

    private void delete() {
        String station = inputView.readDeleteStation();
        StationService.delete(station);
        outputView.printDeleteStation();
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
