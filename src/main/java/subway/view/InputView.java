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

    public String readCreateStation() {
        System.out.println(System.lineSeparator() + Request.CREATE_STATION.value);
        return scanner.nextLine();
    }

    public String readCreateLine() {
        System.out.println(System.lineSeparator() + Request.CREATE_LINE.value);
        return scanner.nextLine();
    }

    public String readUpStation() {
        System.out.println(System.lineSeparator() + Request.CREATE_UP_STATION.value);
        return scanner.nextLine();
    }

    public String readDownStation() {
        System.out.println(System.lineSeparator() + Request.CREATE_DOWN_STATION.value);
        return scanner.nextLine();
    }

    public String readDeleteStation() {
        System.out.println(System.lineSeparator() + Request.DELETE_STATION.value);
        return scanner.nextLine();
    }

    public String readDeleteLine() {
        System.out.println(System.lineSeparator() + Request.DELETE_LINE.value);
        return scanner.nextLine();
    }

    public String readFunction() {
        System.out.println(Request.ENTER_FUNCTION.value);
        return scanner.nextLine();
    }

    public String readLine() {
        System.out.println(Request.ENTER_LINE.value);
        return scanner.nextLine();
    }

    public String readStation() {
        System.out.println(Request.ENTER_STATION.value);
        return scanner.nextLine();
    }

    public String readOrder() {
        System.out.println(Request.ENTER_ORDER.value);
        return scanner.nextLine();
    }

    public String readDeleteSectionLine() {
        System.out.println(System.lineSeparator() + Request.DELETE_SECTION_LINE.value);
        return scanner.nextLine();
    }

    public String readDeleteSectionStation() {
        System.out.println(System.lineSeparator() + Request.DELETE_SECTION_STATION.value);
        return scanner.nextLine();
    }

    private enum Request {
        ENTER_FUNCTION("## 원하는 기능을 선택하세요."),
        CREATE_STATION("## 등록할 역 이름을 입력하세요."),
        DELETE_STATION("## 삭제할 역 이름을 입력하세요."),
        CREATE_LINE("## 등록할 노선 이름을 입력하세요."),
        DELETE_LINE("## 삭제할 노선 이름을 입력하세요."),
        CREATE_UP_STATION("## 등록할 노선의 상행 종점역 이름을 입력하세요."),
        CREATE_DOWN_STATION("## 등록할 노선의 하행 종점역 이름을 입력하세요."),
        ENTER_LINE("## 노선을 입력하세요."),
        ENTER_STATION("## 역이름을 입력하세요."),
        ENTER_ORDER("## 순서를 입력하세요."),
        DELETE_SECTION_LINE("## 삭제할 구간의 노선을 입력하세요."),
        DELETE_SECTION_STATION("## 삭제할 구간의 역을 입력하세요.");
        private final String value;

        Request(final String value) {
            this.value = value;
        }
    }
}
