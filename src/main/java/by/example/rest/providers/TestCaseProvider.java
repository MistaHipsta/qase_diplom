package by.example.rest.providers;

import by.example.rest.dto.testCases.*;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class TestCaseProvider {
    Faker faker = new Faker();
    public Case getTestCase() {
        List<String> steps = new ArrayList<>();
        steps.add(RandomStringUtils.randomAlphabetic(3));
        return Case.builder()
                .steps(steps)
                .title(faker.company().name())
                .description(faker.lorem().characters(3,9))
                .preconditions(faker.lorem().characters(3,9))
                .postconditions(faker.lorem().characters(3,9))
                .severity(faker.number().numberBetween(1, Severity.values().length))
                .priority(faker.number().numberBetween(1, Priority.values().length))
                .behavior(faker.number().numberBetween(1, Behavior.values().length))
                .type(faker.number().numberBetween(1, Type.values().length))
                .layer(faker.number().numberBetween(1, Layer.values().length))
                .isFlaky(faker.number().numberBetween(1, IsFlaky.values().length))
                .automation(faker.number().numberBetween(1, Automation.values().length))
                .status(faker.number().numberBetween(0, Status.values().length))
                .build();
    }
}
