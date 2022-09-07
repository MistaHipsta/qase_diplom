package by.steps.UI;

import by.example.pages.ProjectPage;
import com.codeborne.selenide.Condition;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.commons.lang.StringUtils;
import org.testng.Assert;

import java.util.Map;

public class CreateProjectStepsUi {

    ProjectPage validProject = new ProjectPage();

    @Given("open project page")
    public void openProjectPage() {
        validProject
                .openProjectPage();
    }

    @And("click on create project button and set next value on Project create field:")
    public void setNextValueOnProjectNameField(DataTable field) {
        Map<String, String> params = field.asMap(String.class, String.class);
        validProject.setProjectName(params.get("Project name"));
        validProject.setProjectCode(params.get("Project code"));
        validProject.setDescription(params.get("Description"));
        validProject.setProjectAccessType(params.get("Project access type"));

        validProject
                .createValidProject(
                        validProject.getProjectName(),
                        validProject.getProjectCode(),
                        validProject.getDescription(),
                        validProject.getProjectAccessType()
                );
    }

    @And("check project name equal to created project")
    public void checkProjectNameEqualToCreatedProject() {
        validProject.checkProjectNameAfterCreate(validProject.getProjectName());
    }

    @And("click dropdown menu on created project")
    public void clickDropdownMenuOnCreatedProject() {
        validProject.clickOnDropDownMenu();
    }

    @And("click delete button")
    public void clickDeleteButton() {
        validProject.getDeleteButton().click();
    }

    @And("click delete project submit button")
    public void clickDeleteProjectButton() {
        validProject.getSubmitButton().click();
    }

    @And("click on settings button")
    public void clickOnSettingsButton() {
        validProject.getSettingButton().click();
    }


    @And("click on input project name field and set next value:")
    public void clickOnInputProjectNameFieldAndSetNextValue(DataTable newName) {
        Map<String, String> params = newName.asMap(String.class, String.class);
        validProject.setProjectName(params.get("Project name new"));
        validProject.setProjectCode(params.get("Project code new"));
        validProject.setDescription(params.get("Description new"));
        validProject.setProjectAccessType(params.get("Project access type"));
        validProject.getInputTitleField().clear();
        validProject.getInputTitleField().sendKeys(params.get("Project name new"));
        validProject.getInputCodeField().clear();
        validProject.getInputCodeField().sendKeys(params.get("Project code new"));
        validProject.getInputDescriptionField().clear();
        validProject.getInputDescriptionField().sendKeys(params.get("Description new"));
    }

    @And("click update settings button")
    public void clickUpdateSettingsButton() {
        validProject.getUpdateButton().click();
    }

    @And("check expected alert message")
    public void checkExpectedAlertMessage() {
        Assert.assertEquals(validProject.getAlertMessage().getText(),
                "Project settings were successfully updated!", "Alert not expected");
    }

    @And("check alert is not visible")
    public void checkAlertIsNotVisible() {
        validProject.getAlertMessage().shouldNotBe(Condition.visible);
    }

    @When("click delete button on project page")
    public void clickDeleteButtonOnProjectPage() {
        validProject.getDeleteButton();
    }

    @When("click dropdown menu on updated project")
    public void clickDropdownMenuOnUpdatedProject() {
        validProject.clickOnDropDownMenu();
    }

    @And("check error message code text")
    public void checkErrorMessageText() {
        Assert.assertEquals(validProject.getErrorMessageOnCodeField().getText()
                , "The code format is invalid.", "Invalid error code text");
    }

    @And("check error message title text")
    public void checkErrorMessageTitleText() {
        Assert.assertEquals(validProject.getErrorMessageOnTitleField().getText()
                , "The title may not be greater than 255 characters.", "Invalid error message text");
    }

    @And("check code name")
    public void checkCodeName() {
        Assert.assertEquals(StringUtils.substring(validProject.getCheckCodeOnProjectPage().getText(), 0, 10)
                , "TESTCODETO", "Project code not match");
    }

}
