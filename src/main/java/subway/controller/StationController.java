package subway.controller;

import subway.InputRoofer;
import subway.domain.StationFunction;
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

    }

    private StationFunction getStationFunction() {
        return InputRoofer.getByRoof(() -> {
            String stationFunctionSource = inputView.readStationFunction();
            return StationFunction.from(stationFunctionSource);
        });
    }
}
