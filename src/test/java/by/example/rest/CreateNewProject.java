package by.example.rest;

import by.example.dto.Project;
import by.example.utils.PropertiesLoader;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateNewProject {
    public String extractedCode;
    @Test
    public void createProject(){
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

        //GET PROJECT /project/{codeProject}
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Token",properties.
                getProperty("Token")).
                pathParams("codeProject",extractedCode).
                log().all().
        when().
                get("https://api.qase.io/v1/project/{codeProject}").
        then().
                statusCode(200);
    }
}
