package by.steps.rest;

import by.example.rest.clients.ProjectApiClient;
import by.example.rest.dto.project.Project;
import by.example.rest.dto.responses.DeletedProj;
import by.example.rest.dto.responses.ProjResp;
import by.example.rest.providers.ProjectProvider;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateProjectSteps {

    ProjectApiClient projectApiClient = new ProjectApiClient();
    Project project;

    @Given("create project")
    public void createProject() {
        project = new ProjectProvider().getProjectValues();
        ProjResp postProject = projectApiClient.postProject(project, 200);

        assertThat(postProject.isStatus()).as("The status is incorrect").isTrue();
        assertThat(postProject.getResult().getCode()).as("The code is incorrect").isEqualTo(project.getCode());

        assertThat(projectApiClient.getProject(project.getCode(), 200))
                .as("The project is not actual")
                .usingRecursiveComparison()
                .comparingOnlyFields("title", "actual_result")
                .isEqualTo(project.getCode());
    }

    @Given("delete project")
    public void deleteProject() {
        project = new ProjectProvider().getProjectValues();
        ProjResp postProject = projectApiClient.postProject(project, 200);
        DeletedProj deletedProj = projectApiClient.deleteProject(project.getCode(), 200);

        assertThat(deletedProj.isStatus())
                .as("The status is incorrect")
                .isTrue();
        Response deletedProjectResponse = projectApiClient.getDeleteProject(project.getCode());

        assertThat(deletedProjectResponse.statusCode())
                .as("Project is not deleted")
                .isEqualTo(404);
    }


//    @And("Create project with empty title")
//    public void createProjectWithEmptyTitle() {
//        Faker faker = new Faker();
//        Properties properties = PropertiesLoader.loadProperties();
//        Project expectedProject = Project.builder()
//                .title("")
//                .code(faker.lorem().characters(10))
//                .build();
//        given().
//                contentType(ContentType.JSON).
//                accept(ContentType.JSON).
//                header("Token",properties.getProperty("Token")).
//                body(expectedProject).
//                log().all().
//        when().
//                post("https://api.qase.io/v1/project").
//        then().
//                statusCode(400).
//                log().all().
//                body("status",equalTo(false), "errorMessage",equalTo("Data is invalid."),
//                        "errorFields[0].field",equalTo("title"),"errorFields[0].error",
//                        equalTo("Title is required."));
//    }
//
//    @And("Create project with empty code")
//    public void createProjectWithEmptyCode() {
//        Faker faker = new Faker();
//        Properties properties = PropertiesLoader.loadProperties();
//        Project expectedProject = Project.builder()
//                .title(faker.lorem().characters(10))
//                .code("")
//                .build();
//        given().
//                contentType(ContentType.JSON).
//                accept(ContentType.JSON).
//                header("Token",properties.getProperty("Token")).
//                body(expectedProject).
//                log().all().
//                when().
//                post("https://api.qase.io/v1/project").
//                then().
//                statusCode(400).
//                log().all().
//                body("status",equalTo(false), "errorMessage",equalTo("Data is invalid."),
//                        "errorFields[0].field",equalTo("code"),"errorFields[0].error",
//                        equalTo("Project code is required."));
//    }
//
//    @And("Delete created project")
//    public void deleteCreatedProject() {
//        Properties properties = PropertiesLoader.loadProperties();
//        given().
//                contentType(ContentType.JSON).
//                accept(ContentType.JSON).
//                header("Token",properties.
//                        getProperty("Token")).
//                log().all().
//        when().
//                delete("https://api.qase.io/v1/project/"+ extractedCode).
//        then().
//                statusCode(200);
//    }
//
//    @And("Check that deleted project is not avaliable")
//    public void checkThatDeletedProjectIsNotAvaliable() {
//        Properties properties = PropertiesLoader.loadProperties();
//        given().
//                contentType(ContentType.JSON).
//                accept(ContentType.JSON).
//                header("Token",properties.
//                        getProperty("Token")).
//                log().all().
//                when().
//                get("https://api.qase.io/v1/project/"+ extractedCode).
//                then().
//                statusCode(404);
//    }
}
