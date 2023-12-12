package subway.view;

public class OutputView {

    public void printHello() {
        System.out.println(Response.HELLO.value);
    }

    public void printError(final String message) {
        System.out.println(message);
    }

    private enum Response {
        HELLO("안녕하세요!");

        private final String value;

        Response(final String value) {
            this.value = value;
        }

        public String getWithEnter() {
            return value + System.lineSeparator();
        }
    }
}
