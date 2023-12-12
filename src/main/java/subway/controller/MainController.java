package subway.controller;

import subway.InputRoofer;
import subway.domain.MainFunction;
import subway.view.InputView;
import subway.view.OutputView;

public class MainController {

    private final InputView inputView;
    private final OutputView outputView;

    public MainController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        init();
        run();
        terminate();
    }

    private void init() {
    }

    public void run() {
        while (true) {
            MainFunction mainFunction = getMainFunction();

            if (mainFunction.isTerminate()) {
                return;
            }

            if (mainFunction.isStation()) {
                StationController stationController = new StationController(inputView, outputView);
                stationController.run();
            }
        }

    }

    private void terminate() {
        inputView.close();
    }

    private MainFunction getMainFunction() {
        return InputRoofer.getByRoof(() -> {
            String mainFunctionSource = inputView.readMainFunction();
            return MainFunction.from(mainFunctionSource);
        });
    }

}
