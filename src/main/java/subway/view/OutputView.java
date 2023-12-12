package subway.view;

import java.util.List;
import subway.response.LineDto;

public class OutputView {

    public void printError(final String message) {
        System.out.println(Response.ENTER + message);
    }

    public void printCreateStation() {
        System.out.println(Response.CREATE_STATION.getWithEnter());
    }

    public void printDeleteStation() {
        System.out.println(Response.DELETE_STATION.getWithEnter());
    }

    public void printStations(final List<String> stationNames) {
        System.out.println(Response.STATIONS.value);
        stationNames.forEach(this::printStation);
    }

    private void printStation(final String stationName) {
        System.out.printf(Response.PREFIX + "%s" + Response.ENTER, stationName);
    }

    public void printCreateLine() {
        System.out.println(Response.CREATE_LINE.getWithEnter());
    }

    public void printDeleteLine() {
        System.out.println(Response.DELETE_LINE.getWithEnter());
    }

    public void printLines(List<String> lineNames) {
        System.out.println(Response.LINES.value);
        lineNames.forEach(this::printLine);
    }

    private void printLine(String lineName) {
        System.out.printf(Response.PREFIX + "%s" + Response.ENTER, lineName);
    }

    public void printMainFunction() {
        System.out.println(Response.MAIN_FUNCTION.value);
    }

    public void printStationFunction() {
        System.out.println(Response.STATION_FUNCTION.value);
    }

    public void printLineFunction() {
        System.out.println(Response.LINE_FUNCTION.value);
    }

    public void printSectionFunction() {
        System.out.println(Response.SECTION_FUNCTION.value);
    }

    public void printCreateSection() {
        System.out.println(Response.CREATE_SECTION.value);
    }

    public void printDeleteSection() {
        System.out.println(Response.DELETE_SECTION.value);
    }

    public void printAllMap(final List<LineDto> lineDtos) {
        System.out.println(Response.MAP.value);
        lineDtos.forEach(this::printMap);
    }

    private void printMap(final LineDto lineDto) {
        printLine(lineDto.getName());
        System.out.println("---");
        lineDto.getStations().forEach(this::printStation);
        System.out.println();
    }

    private enum Response {
        MAIN_FUNCTION("\n## 메인 화면\n"
                + "1. 역 관리\n"
                + "2. 노선 관리\n"
                + "3. 구간 관리\n"
                + "4. 지하철 노선도 출력\n"
                + "Q. 종료\n"
                + "\n"),
        STATION_FUNCTION("## 역 관리 화면\n"
                + "1. 역 등록\n"
                + "2. 역 삭제\n"
                + "3. 역 조회\n"
                + "B. 돌아가기\n"
                + "\n"),
        LINE_FUNCTION("## 노선 관리 화면\n"
                + "1. 노선 등록\n"
                + "2. 노선 삭제\n"
                + "3. 노선 조회\n"
                + "B. 돌아가기\n"
                + "\n"),
        SECTION_FUNCTION("## 구간 관리 화면\n"
                + "1. 구간 등록\n"
                + "2. 구간 삭제\n"
                + "B. 돌아가기\n"
                + "\n"),
        CREATE_STATION("지하철 역이 등록되었습니다."),
        DELETE_STATION("지하철 역이 삭제되었습니다."),
        CREATE_LINE("지하철 노선이 등록되었습니다."),
        DELETE_LINE("지하철 노선이 삭제되었습니다."),
        STATIONS("## 역 목록"),
        LINES("## 노선 목록"),
        CREATE_SECTION("구간이 등록되었습니다."),
        DELETE_SECTION("구간이 삭제되었습니다."),
        MAP("## 지하철 노선도");


        private static final String PREFIX = "[INFO] ";
        private static final String ENTER = System.lineSeparator();
        private final String value;

        Response(final String value) {
            this.value = value;
        }

        public String getWithEnter() {
            return ENTER + PREFIX + value;
        }
    }
}
