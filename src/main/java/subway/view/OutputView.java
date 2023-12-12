package subway.view;

public class OutputView {

    public void printError(final String message) {
        System.out.println(Response.ENTER + message);
    }

    public void printCreateStation() {
        System.out.println(Response.CREATE_STATION.getWithEnter());
    }

    private enum Response {
        CREATE_STATION("지하철 역이 등록되었습니다.");

        private static final String PREFIX = "[INFO] ";
        private static final String ENTER = System.lineSeparator();
        private final String value;

        Response(final String value) {
            this.value = value;
        }

        public String getWithEnter() {
            return ENTER + value;
        }
    }
}
