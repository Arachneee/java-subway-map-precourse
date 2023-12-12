package subway.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readDate() {
        System.out.println(Request.NAME.value);
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }

    public String readMainFunction() {
        System.out.println(Request.MAIN.value);
        return scanner.nextLine();
    }

    private enum Request {
        NAME(""),
        MAIN("## 메인 화면\n"
                + "1. 역 관리\n"
                + "2. 노선 관리\n"
                + "3. 구간 관리\n"
                + "4. 지하철 노선도 출력\n"
                + "Q. 종료\n"
                + "\n"
                + "## 원하는 기능을 선택하세요.");
        private final String value;

        Request(final String value) {
            this.value = value;
        }
    }
}
