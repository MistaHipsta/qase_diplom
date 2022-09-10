package by.example.pages;

import by.example.projectPrivate.ProjectPrivate;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class ProjectPage extends ProjectPrivate {

ProjectPrivate projectName = new ProjectPrivate();

    SelenideElement deleteButton = $(xpath("//*[contains(text(),'Change name')]/../../..//a[@class='text-danger']"));

    SelenideElement submitButton = $(xpath("//button[@type='submit']"));

    SelenideElement settingButton = $(xpath("//div[@data-popper-placement]/div[1]"));

    SelenideElement inputTitleField = $(xpath("//input[@id='inputTitle']"));

    SelenideElement inputCodeField = $(xpath("//input[@id='inputCode']"));

    SelenideElement inputDescriptionField = $(xpath("//textarea[@id='inputDescription']"));

    SelenideElement updateButton = $(xpath("//button[@id='update']"));

    SelenideElement alertMessage = $(xpath("//div[@role='alert']"));

    SelenideElement deleteButtonOnProjectPage = $(xpath("//a[@class='btn btn-cancel']"));

    SelenideElement errorMessageOnCodeField = $(xpath("//div[@class='form-control-feedback']"));

    SelenideElement errorMessageOnTitleField = $(xpath("//div[@class='form-control-feedback']"));

    SelenideElement checkCodeOnProjectPage = $(xpath("//h1"));


    public ProjectPage openProjectPage() {
        Selenide.open("/projects");
        getWebDriver()
                .manage()
                .window()
                .maximize();
        $(id("createButton")).shouldBe(visible);
        return new ProjectPage();
    }

    public ProjectPage checkProjectNameAfterCreate(String projectName) {
        Assert.assertEquals($(xpath(String.format("//a[contains(text(),'%s')]", projectName))).getText()
                , projectName, "Project name not equal created project");
        return new ProjectPage();
    }

    public ProjectPage clickOnDropDownMenu(String projectName) {
        $(xpath(String.format("//a[text()='%s']//ancestor::tr[@class='project-row']//a[contains(@class,'btn-dropdown')]"
                , projectName))).click();
        return new ProjectPage();
    }

    public ProjectPage createValidProject(String projectName,
                                          String projectCode,
                                          String description,
                                          String accessType) {
        $(id("createButton")).click();
        $(id("inputTitle")).sendKeys(projectName);
        $(id("inputCode")).clear();
        $(id("inputCode")).sendKeys(projectCode);
        $(id("inputDescription")).sendKeys(description);
        $(id(String.format("%s-access-type", accessType))).click();
        $(xpath("//button[@type='submit']")).click();
        return new ProjectPage();
    }

    public LoginPage LogOut() {
        $(xpath("//span[@aria-label='Chat']/following::span[1]")).click();
        $(xpath("//span[normalize-space()='Sign out']")).click();
        return new LoginPage();
    }

    public SelenideElement getDeleteButton() {
        return deleteButton;
    }

    public SelenideElement getSubmitButton() {
        return submitButton;
    }

    public SelenideElement getSettingButton() {
        return settingButton;
    }

    public SelenideElement getInputTitleField() {
        return inputTitleField;
    }

    public SelenideElement getInputCodeField() {
        return inputCodeField;
    }

    public SelenideElement getInputDescriptionField() {
        return inputDescriptionField;
    }

    public SelenideElement getUpdateButton() {
        return updateButton;
    }

    public SelenideElement getAlertMessage() {
        return alertMessage;
    }

    public SelenideElement getDeleteButtonOnProjectPage() {
        return deleteButtonOnProjectPage;
    }

    public SelenideElement getErrorMessageOnCodeField() {
        return errorMessageOnCodeField;
    }

    public SelenideElement getErrorMessageOnTitleField() {
        return errorMessageOnTitleField;
    }

    public SelenideElement getCheckCodeOnProjectPage() {
        return checkCodeOnProjectPage;
    }
}
