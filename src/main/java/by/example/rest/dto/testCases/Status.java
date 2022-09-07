package by.example.rest.dto.testCases;

import java.util.Arrays;

public enum Status {

    not(0),
    tobe(1),
    automated(2);

    private final int code;

    Status(int number) {
        this.code = number;
    }

    public int getStatus() {
        return code;
    }

    public static Status getStatusByCode(int code) {
        return Arrays.stream(Status.values())
                .filter(s -> s.getStatus() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no Status with code" + code));
    }
}