package subway.exception;

public enum ErrorMessage {

    INVALID_FUNCTION("선택할 수 없는 기능입니다."),
    DUPLICATE_STATION("이미 등록된 역 이름입니다."),
    LINE_EXISTENCE("노선에 등록된 역은 삭제할 수 없습니다."),
    INVALID_STATION("존재하지 않는 역입니다."),
    DUPLICATE_LINE("중복된 노선 이름입니다."),
    NONE_LINE("존재하지 않는 노선입니다."),
    INVALID_ORDER("잘못된 순서입니다."),
    BLANK("입력이 비어있을 수 없습니다."),
    NOT_NUMBER("숫자가 아닙니다."),
    START_ZERO("0으로 시작할 수 없습니다."),
    INVALID_RANGE("잘못된 범위입니다."),
    INVALID_SIZE("잘못된 크기입니다."),
    INVALID_FORMAT("입력 형식이 잘못되었습니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
