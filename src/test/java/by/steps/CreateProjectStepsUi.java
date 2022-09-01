package by.steps;

import by.example.pages.ProjectPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;

public class CreateProjectStepsUi {

    ProjectPage validProject = new ProjectPage();

    @Given("open project page")
    public void openProjectPage() {
        new ProjectPage()
                .openProjectPage();
    }

    @And("click on create project button and set next value on Project create field:")
    public void setNextValueOnProjectNameField(DataTable field) {
        Map<String, String> params = field.asMap(String.class, String.class);
        validProject.setProjectName(params.get("Project name"));
        validProject.setProjectCode(params.get("Project code"));
        validProject.setDescription(params.get("Description"));
        validProject.setProjectAccessType(params.get("Project access type"));

        new ProjectPage()
                .createValidProject(
                        validProject.getProjectName(),
                        validProject.getProjectCode(),
                        validProject.getDescription(),
                        validProject.getProjectAccessType()
                );
    }

    @And("check project name equal to created project")
    public void checkProjectNameEqualToCreatedProject() {
        Assert.assertEquals($(xpath(String.format("//a[contains(text(),'%s')]", validProject.getProjectName()))).getText()
                , validProject.getProjectName());

    }

    @And("click dropdown menu on created project")
    public void clickDropdownMenuOnCreatedProject() {
        $(xpath(String.format("//a[text()='%s']//ancestor::tr[@class='project-row']//a[contains(@class,'btn-dropdown')]"
                , validProject.getProjectName()))).click();
    }

    SelenideElement projectRow = $(xpath(String.format("//*[contains(text(),'%s')]/../../..",validProject.getProjectName())));

    @And("click delete button")
    public void clickDeleteButton() {
        projectRow.findElement(xpath(".//a[@class='text-danger']")).click();
    }

    @And("click delete project submit button")
    public void clickDeleteProjectButton() {
        $(xpath("//button[@type='submit']")).click();
    }

    @And("click on settings button")
    public void clickOnSettingsButton() {
        $(xpath("//div[@data-popper-placement]/div[1]")).click();
    }


    @And("click on input project name field and set next value:")
    public void clickOnInputProjectNameFieldAndSetNextValue(DataTable newName) {
        Map<String, String> params = newName.asMap(String.class, String.class);
        validProject.setProjectName(params.get("Project name new"));
        validProject.setProjectCode(params.get("Project code new"));
        validProject.setDescription(params.get("Description new"));
        validProject.setProjectAccessType(params.get("Project access type"));
        $(xpath("//input[@id='inputTitle']")).sendKeys(params.get("Project name new"));
        $(xpath("//input[@id='inputCode']")).sendKeys(params.get("Project code new"));
        $(xpath("//textarea[@id='inputDescription']")).sendKeys(params.get("Description new"));
    }

    @And("click update settings button")
    public void clickUpdateSettingsButton() {
        $(xpath("//a[normalize-space()='Settings']")).click();
    }

    @And("check expected alert message")
    public void checkExpectedAlertMessage() {
        Assert.assertEquals($(xpath("//div[@role='alert']")).getText(),
                "Project settings were successfully updated!");
    }

    @And("check alert is not visible")
    public void checkAlertIsNotVisible() {
        $(xpath("//div[@role='alert']")).shouldNotBe(Condition.visible);
    }

    @When("click delete button on project page")
    public void clickDeleteButtonOnProjectPage() {
        $(xpath("//a[@class='btn btn-cancel']")).click();
    }

    @When("click dropdown menu on updated project")
    public void clickDropdownMenuOnUpdatedProject() {
        $(xpath(String.format("//a[text()='%s']//ancestor::tr[@class='project-row']//a[contains(@class,'btn-dropdown')]"
                , validProject.getProjectName()))).click();
    }
}
