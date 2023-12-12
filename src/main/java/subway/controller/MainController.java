package subway.controller;

import java.util.function.Supplier;
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

    public void run() {
        String mainFunctionSource = inputView.readMainFunction();
        MainFunction mainFunction = MainFunction.from(mainFunctionSource);


        inputView.close();
    }

    private Object getInput() {
        return getByRoof(() -> {
            // TODO
            return null;
        });
    }

    private <T> T getByRoof(final Supplier<T> method) {
        while (true) {
            try {
                return method.get();
            } catch (IllegalArgumentException illegalArgumentException) {
                outputView.printError(illegalArgumentException.getMessage());
            }
        }
    }

}
