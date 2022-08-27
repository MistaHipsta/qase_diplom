package by.steps;

import by.example.dto.Project;
import by.example.pages.LoginPage;
import by.example.pages.ProjectPage;
import by.example.utils.PropertiesLoader;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;

import java.util.Map;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

//api
@Log4j2
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

    @And("click on create button")
    public void clickOn() {
        $(id("createButton")).shouldBe(Condition.visible).click();
    }


    @And("set next value on Project name field:")
    public void setNextValueOnField(DataTable projectName) {
        Map<String, String> params = projectName.asMap(String.class, String.class);
        $(id("inputTitle")).shouldBe(Condition.visible).sendKeys(params.get("Project name"));
    }

    @And("set next value on Project code field:")
    public void setNextValueOnProjectCodeField(DataTable projectCode) {
        Map<String, String> params = projectCode.asMap(String.class, String.class);
        $(id("inputCode")).clear();
        $(id("inputCode")).shouldBe(Condition.visible).sendKeys(params.get("Project code"));
        PropertiesLoader.loadProperties("configuration").setProperty("projectCode",params.get("Project code"));
    }

    @And("set next value on Description field:")
    public void setNextValueOnDescriptionField(DataTable description) {
        Map<String, String> params = description.asMap(String.class, String.class);
        $(id("inputDescription")).shouldBe(Condition.visible).sendKeys(params.get("Description"));
        PropertiesLoader.loadProperties("configuration").setProperty("descriptionProject",params.get("Description"));
    }

    @And("set public project access type")
    public void setProjectAccessType() {
        $(id("public-access-type")).shouldBe(Condition.visible).click();
    }

    @Then("click on Create project button")
    public void clickOnCreateProjectButton() {
        $(xpath("//button[@type='submit']")).shouldBe(Condition.visible).click();

    }

    @And("click dropdown menu on created project")
    public void clickDropdownMenuOnCreatedProject() {
        $(xpath("//a[text()='Test project name']//ancestor::tr[@class='project-row']//a[contains(@class,'btn-dropdown')]")).click();
    }

    @And("click delete button")
    public void clickDeleteButton() {
        $(xpath("//a[@class='text-danger']")).click();
    }

    @And("click delete project button")
    public void clickDeleteProjectButton() {
        $(xpath("//button[@type='submit']")).click();
    }

    @When("go to project page")
    public void goToProjectPage() {
        Selenide.open("https://app.qase.io/projects");
    }

    @And("check project name equal to created project")
    public void checkProjectNameEqualToCreatedProject() {
        Assert.assertEquals($(xpath("//div[contains(text(),'Test project name')]")),
                PropertiesLoader.loadProperties("configuration").getProperty("projectNameProp"));
    }
}
