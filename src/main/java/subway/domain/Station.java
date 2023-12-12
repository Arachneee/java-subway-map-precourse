package subway.domain;

public class Station {
    private String name;

    public Station(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {

    }

    public String getName() {
        return name;
    }

    // 추가 기능 구현
}
