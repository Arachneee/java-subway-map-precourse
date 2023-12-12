package subway;

import java.util.function.Supplier;
import subway.view.OutputView;

public final class InputRoofer {

    private static final OutputView outputView = new OutputView();

    private InputRoofer() {
    }

    public static <T> T getByRoof(final Supplier<T> method) {
        while (true) {
            try {
                return method.get();
            } catch (IllegalArgumentException illegalArgumentException) {
                outputView.printError(illegalArgumentException.getMessage());
            }
        }
    }
}
