package by.example.rest;

import by.example.dto.Project;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateNewProject {

    @Test
    public void createProject(){
        Project expectedProject = Project.builder()
                .title("Test QA1") //TODO add faker
                .code("Example1")
                .build();
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Token","b986b70ee999b50414b1e4ad933b477a36c4c098").
                body(expectedProject).

        when().
                post("https://api.qase.io/v1/project").
        then().
                statusCode(200).
                body("status",equalTo(true));
    }
}
