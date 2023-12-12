package subway.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void close() {
        scanner.close();
    }

    public String readMainFunction() {
        System.out.println(Request.MAIN_FUNCTION.value + Request.ENTER_FUNCTION.value);
        return scanner.nextLine();
    }

    public String readStationLineFunction() {
        System.out.println(Request.STATION_FUNCTION.value + Request.ENTER_FUNCTION.value);
        return scanner.nextLine();
    }

    public String readStation() {
        System.out.println(System.lineSeparator() + Request.CREATE_STATION.value);
        return scanner.nextLine();
    }

    public String readLine() {
        System.out.println(System.lineSeparator() + Request.CREATE_LINE.value);
        return scanner.nextLine();
    }

    private enum Request {
        NAME(""),
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
        ENTER_FUNCTION("## 원하는 기능을 선택하세요."),
        CREATE_STATION("## 등록할 역 이름을 입력하세요."),
        CREATE_LINE("## 등록할 노선 이름을 입력하세요.");
        private final String value;

        Request(final String value) {
            this.value = value;
        }
    }
}
