package subway.controller;

import subway.InputRoofer;
import subway.domain.Line;
import subway.domain.SectionFunction;
import subway.domain.Station;
import subway.service.LineService;
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
        Line line = getCreateLine();
        Station upStation = getUpStation();
        Station downStation = getDownStation();

        try {
            LineService.create(line, upStation, downStation);
            outputView.printCreateLine();
        } catch (IllegalArgumentException illegalArgumentException) {
            outputView.printError(illegalArgumentException.getMessage());
        }
    }

    private void delete() {
        Line line = getDeleteLine();

        try {
            LineService.delete(line);
            outputView.printDeleteLine();
        } catch (IllegalArgumentException illegalArgumentException) {
            outputView.printError(illegalArgumentException.getMessage());
        }
    }
}
