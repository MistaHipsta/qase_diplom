package by.steps.API;

import by.example.dto.Case;
import by.example.dto.OnlyRequiredFields;
import by.example.dto.Project;

import by.example.dto.Step;
import by.example.utils.PropertiesLoader;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
@Log4j2
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
//                .attachments(List.of(faker.lorem().characters(10),
//                        faker.lorem().characters(10)))
                .steps(List.of(Step.builder()
                        .expected_result(faker.lorem().characters(10))
                        .action(faker.lorem().characters(10))
                        .data(faker.lorem().characters(10))
                        .position(1)
                        .build()))
                //.tags(List.of("12","13"))
                .title(faker.lorem().characters(10))
                .description(faker.lorem().characters(10))
                .preconditions(faker.lorem().characters(10))
                .postconditions(faker.lorem().characters(10))
                .severity(1)
                .priority(1)
                .behavior(1)
                .type(1)
                .layer(1)
                .is_flaky(1)
                //.suite_id(Integer.parseInt(faker.number().digits(2)))
                //.milestone_id(Integer.parseInt(faker.number().digits(2)))
                .automation(0)
                .status(1)
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

                body("status",equalTo(true));
        log.error("123");
    }

    @And("Update test case")
    public void updateTestCase() {
        Faker faker = new Faker();
        Properties properties = PropertiesLoader.loadProperties();
        Case expectedTestCase = Case.builder()
//                .attachments(List.of(faker.lorem().characters(10),
//                        faker.lorem().characters(10)))
                .steps(List.of(Step.builder()
                        .expected_result(faker.lorem().characters(10))
                        .action(faker.lorem().characters(10))
                        .data(faker.lorem().characters(10))
                        .position(1)
                        .build()))
                //.tags(List.of("12","13"))
                .title(faker.lorem().characters(10))
                .description(faker.lorem().characters(10))
                .preconditions(faker.lorem().characters(10))
                .postconditions(faker.lorem().characters(10))
                .severity(2)
                .priority(2)
                .behavior(2)
                .type(2)
                .layer(2)
                .is_flaky(0)
                //.suite_id(Integer.parseInt(faker.number().digits(2)))
                //.milestone_id(Integer.parseInt(faker.number().digits(2)))
                .automation(1)
                .status(2)
                .build();
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Token",properties.getProperty("Token")).
                body(expectedTestCase).
                log().all().
        when().
                patch("https://api.qase.io/v1/case/"+ extractedCode + "/" + idTestcase).
        then().
                statusCode(200).
                log().all().
                body("status",equalTo(true));
    }

    @And("Get a specific test case")
    public void getASpecificTestCase() {
        Properties properties = PropertiesLoader.loadProperties();
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Token",properties.getProperty("Token")).
                log().all().
        when().
                get("https://api.qase.io/v1/case/" + extractedCode + "/" + idTestcase).
        then().
                statusCode(200).log().all();
    }

    @And("Delete test case")
    public void deleteTestCase() {
        Properties properties = PropertiesLoader.loadProperties();
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Token",properties.getProperty("Token")).
                log().all().
                when().
                delete("https://api.qase.io/v1/case/" + extractedCode + "/" + idTestcase).
                then().
                statusCode(200).log().all();
    }
}
