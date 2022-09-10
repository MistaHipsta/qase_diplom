package by.example.rest.dto.testCases;

import java.util.Arrays;

public enum Priority {

    undefined(0),
    hight(1),
    medium(2),
    low(3);

    private final int code;

    Priority(int number) {
        this.code = number;
    }

    public int getPriority() {
        return code;
    }

    public static Priority getPriorityByCode(int code) {
        return Arrays.stream(Priority.values())
                .filter(s -> s.getPriority() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no Priority with code" + code));
    }
}