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
        MainFunction mainFunction = getMainFunction();


        inputView.close();
    }

    private MainFunction getMainFunction() {
        return getByRoof(() -> {
            String mainFunctionSource = inputView.readMainFunction();
            return MainFunction.from(mainFunctionSource);
        });
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
