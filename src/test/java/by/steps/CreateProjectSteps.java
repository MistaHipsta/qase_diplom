package by.steps;

import by.example.rest.clients.ProjectApiClient;
import by.example.rest.dto.project.Project;
import by.example.rest.dto.responses.ProjResp;
import by.example.rest.providers.ProjectProvider;
import io.cucumber.java.en.Given;

import static org.assertj.core.api.Assertions.assertThat;


public class CreateProjectSteps {
    ProjectApiClient projectApiClient = new ProjectApiClient();
    Project project;

    @Given("Create project with valid values")
    public void createNewProject() {
        project = new ProjectProvider().getProject();
        ProjResp postProject = projectApiClient.postProject( project,200);
        assertThat(postProject.isStatus()).as("Ne sozdalos").isEqualTo(true);
        assertThat(postProject.getResult().getCode()).as("Ne vernii code")
                .isEqualTo(project.getCode().toUpperCase());
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
