package by.steps;

import by.example.dto.Project;
import by.example.pages.LoginPage;
import by.example.pages.ProjectPage;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

//api
public class CreateProjectSteps {
    @io.cucumber.java.en.Given("^Create new project with random name and code$")
    public void createNewProjectWithRandomNameAndCode() {
            Project expectedProject = Project.builder()
                    .title("Test QA1") //TODO add faker
                    .code("Example1")
                    .build();
            given().
                    contentType(ContentType.JSON).
                    accept(ContentType.JSON).
                    header("Token", "b986b70ee999b50414b1e4ad933b477a36c4c098").
                    body(expectedProject).

                    when().
                    post("https://api.qase.io/v1/project").
                    then().
                    statusCode(200).
                    body("status", equalTo(true));
        }
//UI
    @Given("open project page")
    public void openProjectPage() {
        new LoginPage()
                .open()
                .loginValidUser();
    }
}
