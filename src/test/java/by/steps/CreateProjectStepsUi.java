package by.steps;

import by.example.pages.ProjectPage;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
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

    SelenideElement projectRow = $(xpath("//*[contains(text(),'Test project name')]/../../.."));

    @And("click delete button")
    public void clickDeleteButton() {
        projectRow.findElement(xpath(".//a[@class='text-danger']")).click();
    }

    @And("click delete project submit button")
    public void clickDeleteProjectButton() {
        $(xpath("//button[@type='submit']")).click();
    }
}
