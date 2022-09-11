package by.example.rest.dto.testCases;

import java.util.Arrays;

public enum IsFlaky {

    undefined(0),
    e2e(1);

    private final int code;

    IsFlaky(int number) {
        this.code = number;
    }

    public int getIsFlaky() {
        return code;
    }

    public static IsFlaky getIsFlakyByCode(int code) {
        return Arrays.stream(IsFlaky.values())
                .filter(s -> s.getIsFlaky() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no IsFlaky with code" + code));
    }
}