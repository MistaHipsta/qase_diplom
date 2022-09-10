package by.example.rest.dto.testCases;

import java.util.Arrays;

public enum Behavior {

    undefined(0),
    positive(1),
    negative(2),
    destructive(3);

    private final int code;

    Behavior(int number) {
        this.code = number;
    }

    public int getBehavior() {
        return code;
    }

    public static Behavior getBehaviorByCode(int code) {
        return Arrays.stream(Behavior.values())
                .filter(s -> s.getBehavior() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no Behavior with code" + code));
    }
}