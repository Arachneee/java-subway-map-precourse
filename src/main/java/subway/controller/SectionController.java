package subway.controller;

import subway.InputRoofer;
import subway.controller.parser.Parser;
import subway.domain.SectionFunction;
import subway.service.SectionService;
import subway.view.InputView;
import subway.view.OutputView;

public class SectionController {

    private final InputView inputView;
    private final OutputView outputView;

    public SectionController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        SectionFunction sectionFunction = getSectionFunction();

        if (sectionFunction.isCreate()) {
            create();
            return;
        }

        if (sectionFunction.isDelete()) {
            delete();
        }

        // SectionFunction is BACK
    }

    private SectionFunction getSectionFunction() {
        outputView.printSectionFunction();

        return InputRoofer.getByRoof(() -> {
            String functionSource = inputView.readFunction();
            return SectionFunction.from(functionSource);
        });
    }

    private void create() {
        String line = inputView.readLine();
        String station = inputView.readStation();
        int order = Parser.convertToInt(inputView.readOrder());

        try {
            SectionService.create(line, station, order);
            outputView.printCreateSection();
        } catch (IllegalArgumentException illegalArgumentException) {
            outputView.printError(illegalArgumentException.getMessage());
        }
    }

    private void delete() {
        String line = inputView.readDeleteSectionLine();
        String station = inputView.readDeleteSectionStation();

        try {
            SectionService.delete(line, station);
            outputView.printDeleteSection();
        } catch (IllegalArgumentException illegalArgumentException) {
            outputView.printError(illegalArgumentException.getMessage());
        }
    }
}
