package by.example.rest.dto.testCases;

import java.util.Arrays;

public enum Layer {

    undefined(0),
    e2e(1),
    api(2),
    unit(3);

    private final int code;

    Layer(int number) {
        this.code = number;
    }

    public int getLayer() {
        return code;
    }

    public static Layer getLayerByCode(int code) {
        return Arrays.stream(Layer.values())
                .filter(s -> s.getLayer() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no Layer with code" + code));
    }
}