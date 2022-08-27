package by.steps;

import by.example.dto.Case;
import by.example.dto.OnlyRequiredFields;
import by.example.dto.Project;

import by.example.dto.Step;
import by.example.utils.PropertiesLoader;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;

import java.util.List;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateTestCaseSteps {
    String extractedCode;
    String idTestcase;

    @Given("Create test case only with requered fields")
    public void createTestCaseWOnlyWithRequaredFields() {
        // POST PROJECT /project
        Faker faker = new Faker();
        Properties properties = PropertiesLoader.loadProperties();
        Project expectedProject = Project.builder()
                .title(faker.lorem().characters(10))
                .code(faker.lorem().characters(10))
                .build();
        extractedCode = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Token",properties.getProperty("Token")).
                body(expectedProject).
                log().all().
                when().
                post("https://api.qase.io/v1/project").
                then().
                statusCode(200).
                log().all().
                body("status",equalTo(true)).
                extract().body().jsonPath().getString("result.code");

        // POST TEST CASE /case/{code}
       OnlyRequiredFields expectedTestCase = OnlyRequiredFields.builder()
                .title(faker.lorem().characters(10))
                .build();
         idTestcase = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Token",properties.getProperty("Token")).
                body(expectedTestCase).
                log().all().
         when().
                post("https://api.qase.io/v1/case/" + extractedCode).
         then().
                statusCode(200).
                log().all().
                body("status",equalTo(true)).
                extract().body().jsonPath().getString("result.id");
    }

    @And("Create test case with all fields")
    public void createTestCaseWithAllFields() {
        Faker faker = new Faker();
        Properties properties = PropertiesLoader.loadProperties();
        Case expectedTestCase = Case.builder()
                .attachments(List.of(faker.lorem().characters(10),
                        faker.lorem().characters(10)))


                .steps(List.of(Step.builder()
                        .expected_result(faker.lorem().characters(10))
                        .action(faker.lorem().characters(10))
                        .data(faker.lorem().characters(10))
                        .position(Integer.parseInt(faker.number().digits(2)))
                        .build())
                )
                .tags(List.of("12"))
                .title(faker.lorem().characters(10))
                .description(faker.lorem().characters(10))
                .preconditions(faker.lorem().characters(10))
                .postconditions(faker.lorem().characters(10))
                .severity(Integer.parseInt(faker.number().digits(2)))
                .priority(Integer.parseInt(faker.number().digits(2)))
                .behavior(Integer.parseInt(faker.number().digits(2)))
                .type(Integer.parseInt(faker.number().digits(2)))
                .layer(Integer.parseInt(faker.number().digits(2)))
                .is_flaky(Integer.parseInt(faker.number().digits(2)))
                //.suite_id(Integer.parseInt(faker.number().digits(2)))
                //.milestone_id(Integer.parseInt(faker.number().digits(2)))
                .automation(Integer.parseInt(faker.number().digits(2)))
                .status(Integer.parseInt(faker.number().digits(2)))


                .build();
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Token",properties.getProperty("Token")).
                body(expectedTestCase).
                log().all().
                when().
                post("https://api.qase.io/v1/case/"+extractedCode).
                then().
                statusCode(200).
                log().all().
                body("status",equalTo(true)).
                extract().body().jsonPath().getString("result.code");
    }
}
