package by.example.rest.dto.testCases;

import java.util.Arrays;

public enum Type {

    undefined(0),
    functional(1),
    smoke(2),
    regression(3),
    security(4),
    usability(5),
    performance(6),
    acceptable(7),
    compatibility(8),
    integration(9),
    exploratory(10);

    private final int code;

    Type(int number) {
        this.code = number;
    }

    public int getType() {
        return code;
    }

    public static Type getTypeByCode(int code) {
        return Arrays.stream(Type.values())
                .filter(s -> s.getType() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no Type with code" + code));
    }
}