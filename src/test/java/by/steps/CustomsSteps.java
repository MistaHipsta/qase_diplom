package by.steps;

import io.cucumber.java.en.And;

import static com.codeborne.selenide.Selenide.sleep;

public class CustomsSteps {
    @And("^wait (\\d+) (?:second|seconds)")
    public void waitSecond(long seconds) {
        sleep(1000 * seconds);
    }
}
