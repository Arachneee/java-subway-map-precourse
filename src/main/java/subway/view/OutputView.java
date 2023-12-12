package subway.view;

import java.util.List;

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

    private enum Response {
        CREATE_STATION("지하철 역이 등록되었습니다."),
        DELETE_STATION("지하철 역이 삭제되었습니다."),
        CREATE_LINE("지하철 노선이 등록되었습니다."),
        DELETE_LINE("지하철 노선이 삭제되었습니다."),
        STATIONS("## 역 목록"),
        LINES("## 노선 목록");

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
