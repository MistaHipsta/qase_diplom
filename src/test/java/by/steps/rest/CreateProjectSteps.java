package by.steps.rest;

import by.example.rest.clients.ProjectApiClient;
import by.example.rest.dto.project.Project;
import by.example.rest.dto.responses.DeletedProj;
import by.example.rest.dto.responses.ProjResp;
import by.example.rest.providers.ProjectProvider;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.opentest4j.AssertionFailedError;

import static org.assertj.core.api.Assertions.assertThat;
@Slf4j
public class CreateProjectSteps {

    ProjectApiClient projectApiClient = new ProjectApiClient();
    Project project;

    @Given("create project")
    public void createProject() {

        log.info("start create project");
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

    @And("delete project")
    public void deleteProject() {
        log.info("start delete project");
        project = new ProjectProvider().getProjectValues();
        projectApiClient.postProject(project, 200);
        DeletedProj deletedProj = projectApiClient.deleteProject(project.getCode(), 200);

        assertThat(deletedProj.isStatus())
                .as("The status is incorrect")
                .isTrue();
        Response deletedProjectResponse = projectApiClient.getDeleteProject(project.getCode());
        try {
            throw new AssertionError("error delete project");
        }catch (AssertionError e){
            log.error("error delete project",e);
        }
        assertThat(deletedProjectResponse.statusCode())
                .as("Project is not deleted")
                .isEqualTo(704);

    }
}