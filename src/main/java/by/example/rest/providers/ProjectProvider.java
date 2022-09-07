package by.example.rest.providers;

import by.example.rest.dto.project.Project;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class ProjectProvider {
    Faker faker = new Faker();
    public Project getProjectValues() {
        return Project.builder()
                .title(faker.lorem().characters(3,9).toUpperCase())
                .code(faker.lorem().characters(3,9).toUpperCase())
                .description(faker.lorem().characters(3,9).toUpperCase())
                .build();
    }

}
