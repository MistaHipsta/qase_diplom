package by.steps.rest;

import by.example.rest.clients.ProjectApiClient;
import by.example.rest.clients.TestCaseApiClient;
import by.example.rest.dto.project.Project;
import by.example.rest.dto.responses.ProjResp;
import by.example.rest.dto.responses.TestCaseResponse.TestCaseById;
import by.example.rest.dto.responses.TestCaseResponse.TestCaseResponse;
import by.example.rest.dto.testCases.Case;
import by.example.rest.providers.ProjectProvider;
import by.example.rest.providers.TestCaseProvider;
import io.cucumber.java.en.Given;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CreateTestCaseSteps {
    ProjectApiClient projectApiClient = new ProjectApiClient();
    TestCaseApiClient testCaseApiClient = new TestCaseApiClient();

    Project project;

    @Given("create test case")
    public void createTestCase() {

        project = new ProjectProvider().getProjectValues();
        ProjResp postProject = projectApiClient.postProject(project, 200);

        Case expectedTestCase = new TestCaseProvider().getTestCase();
        TestCaseResponse postActualTestCase = testCaseApiClient.postTestCase(expectedTestCase, postProject.getResult().getCode(), 200);

        TestCaseById actualTestCase = testCaseApiClient.getTestCase(project.getCode(), postActualTestCase.getResult().getId(), 200);
        assertThat(actualTestCase).as("Test Case are different").usingRecursiveComparison()
                .comparingOnlyFields("title", "actual_result")
                .isEqualTo(expectedTestCase);

    }

}
