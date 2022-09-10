package by.steps.API;

import by.example.dto.Project;
import by.example.utils.PropertiesLoader;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.cucumber.java.en.Given;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

//api
public class CreateProjectStepsApi {
    @Given("^Create new project with random name and code$")
    public void createNewProjectWithRandomNameAndCode() {
        Faker faker = new Faker();
            Project expectedProject = Project.builder()
                    .title(faker.lorem().characters(10))
                    .code(faker.lorem().characters(10))
                    .build();
            given().
                    contentType(ContentType.JSON).
                    accept(ContentType.JSON).
                    header("Token",PropertiesLoader.loadProperties("Token")).
                    body(expectedProject).

            when().
                    post("https://api.qase.io/v1/project").
            then().
                    statusCode(200)
                    .body("status", equalTo(true));
        }
}
