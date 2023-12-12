package subway.controller;

import subway.InputRoofer;
import subway.controller.parser.Parser;
import subway.domain.Line;
import subway.domain.Order;
import subway.domain.SectionFunction;
import subway.domain.Station;
import subway.service.LineService;
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
        Line line = getLine();
        Station station = getStation();
        Order order = getOrder();

        try {
            SectionService.create(line, station, order);
            outputView.printCreateLine();
        } catch (IllegalArgumentException illegalArgumentException) {
            outputView.printError(illegalArgumentException.getMessage());
        }
    }

    private Order getOrder() {
        return InputRoofer.getByRoof(() -> {
            String order = inputView.readOrder();
            return new Order(Parser.convertToInt(order));
        });
    }

    private Station getStation() {
        return InputRoofer.getByRoof(() -> {
            String station = inputView.readStation();
            return new Station(station);
        });
    }

    private Line getLine() {
        return InputRoofer.getByRoof(() -> {
            String line = inputView.readLine();
            return new Line(line);
        });
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
