package subway.controller;

import java.util.List;
import java.util.stream.Collectors;
import subway.util.InputRoofer;
import subway.domain.Line;
import subway.domain.StationLineFunction;
import subway.service.LineService;
import subway.view.InputView;
import subway.view.OutputView;

public class LineController {
    private final InputView inputView;
    private final OutputView outputView;

    public LineController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        InputRoofer.runRoof(() -> {
            StationLineFunction lineFunction = getLineFunction();

            if (lineFunction.isCreate()) {
                create();
                return;
            }

            if (lineFunction.isDelete()) {
                delete();
                return;
            }

            if (lineFunction.isRead()) {
                read();
            }

            // LineFunction is BACK
        });
    }

    private StationLineFunction getLineFunction() {
        outputView.printLineFunction();
        return InputRoofer.getByRoof(() -> {
            String functionSource = inputView.readFunction();
            return StationLineFunction.from(functionSource);
        });
    }

    private void create() {
        String line = inputView.readCreateLine();
        String upStation = inputView.readUpStation();
        String downStation = inputView.readDownStation();

        LineService.create(line, upStation, downStation);
        outputView.printCreateLine();
    }

    private void delete() {
        String line = inputView.readDeleteLine();

        LineService.delete(line);
        outputView.printDeleteLine();
    }

    private void read() {
        List<Line> lines = LineService.readAll();
        List<String> lineNames = createLineNames(lines);
        outputView.printLines(lineNames);
    }

    private static List<String> createLineNames(List<Line> lines) {
        return lines.stream()
                .map(Line::getName)
                .collect(Collectors.toList());
    }
}
