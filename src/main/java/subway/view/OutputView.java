package subway.view;

import java.util.List;
import subway.response.StationDto;

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

    public void printStations(final List<StationDto> stationDtos) {
        stationDtos.forEach(this::printStation);
    }

    private void printStation(final StationDto stationDto) {
        System.out.printf(Response.PREFIX + "%s" + Response.ENTER, stationDto.getName());
    }

    public void printCreateLine() {
        System.out.println(Response.CREATE_LINE.getWithEnter());
    }

    public void printDeleteLine() {
        System.out.println(Response.DELETE_LINE.getWithEnter());
    }

    private enum Response {
        CREATE_STATION("지하철 역이 등록되었습니다."),
        DELETE_STATION("지하철 역이 삭제되었습니다."),
        CREATE_LINE("지하철 노선이 등록되었습니다."),
        DELETE_LINE("지하철 노선이 삭제되었습니다.");

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
