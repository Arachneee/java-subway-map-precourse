package subway.controller;


import subway.InputRoofer;
import subway.domain.Station;
import subway.domain.StationFunction;
import subway.domain.StationRepository;
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
            StationRepository.addStation(station);
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

        StationRepository.deleteStation(station);
    }

    private void read() {

    }

    private StationFunction getStationFunction() {
        return InputRoofer.getByRoof(() -> {
            String stationFunctionSource = inputView.readStationFunction();
            return StationFunction.from(stationFunctionSource);
        });
    }
}
