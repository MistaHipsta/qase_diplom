package by.steps;

import by.example.dto.Project;
import by.example.pages.LoginPage;
import by.example.utils.PropertiesLoader;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

//api
public class CreateProjectSteps {
    String extractedCode;
//UI
    @Given("open project page")
    public void openProjectPage() {
        new LoginPage()
                .open()
                .loginValidUser();
    }

    @Given("Create project with valid values")
    public void createNewProject() {
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

    }

    @And("Check created project with valid values")
    public void getCreatedProject() {
            Properties properties = PropertiesLoader.loadProperties();
            given().
                    contentType(ContentType.JSON).
                    accept(ContentType.JSON).
                    header("Token",properties.
                    getProperty("Token")).
                    log().all().
            when().
                    get("https://api.qase.io/v1/project/"+ extractedCode).
            then().
                    statusCode(200);
    }


    @And("Create project with empty title")
    public void createProjectWithEmptyTitle() {
        Faker faker = new Faker();
        Properties properties = PropertiesLoader.loadProperties();
        Project expectedProject = Project.builder()
                .title("")
                .code(faker.lorem().characters(10))
                .build();
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Token",properties.getProperty("Token")).
                body(expectedProject).
                log().all().
        when().
                post("https://api.qase.io/v1/project").
        then().
                statusCode(400).
                log().all().
                body("status",equalTo(false), "errorMessage",equalTo("Data is invalid."),
                        "errorFields[0].field",equalTo("title"),"errorFields[0].error",
                        equalTo("Title is required."));
    }

    @And("Create project with empty code")
    public void createProjectWithEmptyCode() {
        Faker faker = new Faker();
        Properties properties = PropertiesLoader.loadProperties();
        Project expectedProject = Project.builder()
                .title(faker.lorem().characters(10))
                .code("")
                .build();
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Token",properties.getProperty("Token")).
                body(expectedProject).
                log().all().
                when().
                post("https://api.qase.io/v1/project").
                then().
                statusCode(400).
                log().all().
                body("status",equalTo(false), "errorMessage",equalTo("Data is invalid."),
                        "errorFields[0].field",equalTo("code"),"errorFields[0].error",
                        equalTo("Project code is required."));
    }

    @And("Delete created project")
    public void deleteCreatedProject() {
        Properties properties = PropertiesLoader.loadProperties();
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Token",properties.
                        getProperty("Token")).
                log().all().
        when().
                delete("https://api.qase.io/v1/project/"+ extractedCode).
        then().
                statusCode(200);
    }

    @And("Check that deleted project is not avaliable")
    public void checkThatDeletedProjectIsNotAvaliable() {
        Properties properties = PropertiesLoader.loadProperties();
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Token",properties.
                        getProperty("Token")).
                log().all().
                when().
                get("https://api.qase.io/v1/project/"+ extractedCode).
                then().
                statusCode(404);
    }
}
