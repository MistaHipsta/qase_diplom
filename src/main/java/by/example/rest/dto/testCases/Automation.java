package by.example.rest.dto.testCases;

import java.util.Arrays;

public enum Automation {

    not(0),
    tobe(1),
    automated(2);

    private final int code;

    Automation(int number) {
        this.code = number;
    }

    public int getAutomation() {
        return code;
    }

    public static Automation getAutomationByCode(int code) {
        return Arrays.stream(Automation.values())
                .filter(s -> s.getAutomation() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no Automation with code" + code));
    }
}