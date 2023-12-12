package subway.controller;

import java.util.List;
import java.util.stream.Collectors;
import subway.util.InputRoofer;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.MainFunction;
import subway.domain.Order;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.response.LineDto;
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
        List<String> stationNames = List.of("교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역");
        List<String> lineNames = List.of("2호선", "3호선", "신분당선");

        stationNames.forEach(stationName -> StationRepository.addStation(new Station(stationName)));
        lineNames.forEach(lineName -> LineRepository.addLine(new Line(lineName)));

        Line twoLine = LineRepository.findByLineName("2호선");
        Line threeLine = LineRepository.findByLineName("3호선");
        Line shinbundangLine = LineRepository.findByLineName("신분당선");

        Station gyodaeStation = StationRepository.findByStationName("교대역");
        Station gangnamStation = StationRepository.findByStationName("강남역");
        Station yeoksamStation = StationRepository.findByStationName("역삼역");
        Station nambuTerminalStation = StationRepository.findByStationName("남부터미널역");
        Station yangjaeStation = StationRepository.findByStationName("양재역");
        Station maebongStation = StationRepository.findByStationName("매봉역");
        Station yangjaeCitizenSForestStation = StationRepository.findByStationName("양재시민의숲역");

        Order order = new Order(1);

        twoLine.create(gyodaeStation, yeoksamStation);
        twoLine.addSection(gangnamStation, order);

        threeLine.create(gyodaeStation, maebongStation);
        threeLine.addSection(yangjaeStation, order);
        threeLine.addSection(nambuTerminalStation, order);

        shinbundangLine.create(gangnamStation, yangjaeCitizenSForestStation);
        shinbundangLine.addSection(yangjaeStation, order);
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
                continue;
            }

            if (mainFunction.isMap()) {
                printAllMap();
            }
        }

    }

    private void printAllMap() {
        List<Line> lines = LineRepository.lines();
        List<LineDto> lineDtos = lines.stream()
                .map(LineDto::from)
                .collect(Collectors.toList());

        outputView.printAllMap(lineDtos);
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
