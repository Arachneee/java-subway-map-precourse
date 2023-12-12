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
                continue;
            }

            if (mainFunction.isLine()) {
                LineController lineController = new LineController(inputView, outputView);
                lineController.run();
                continue;
            }

            if (mainFunction.isSection()) {
                SectionController sectionController = new SectionController(inputView, outputView);
                sectionController.run();
            }
        }

    }

    private void terminate() {
        inputView.close();
    }

    private MainFunction getMainFunction() {
        outputView.printMainFunction();
        return InputRoofer.getByRoof(() -> {
            String mainFunctionSource = inputView.readFunction();
            return MainFunction.from(mainFunctionSource);
        });
    }

}
