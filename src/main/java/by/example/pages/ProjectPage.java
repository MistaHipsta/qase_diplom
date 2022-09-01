package by.example.pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class ProjectPage {

    private String projectName;
    private String projectCode;
    private String description;
    private String projectAccessType;

    public ProjectPage openProjectPage() {
        Selenide.open("/projects");
        getWebDriver()
                .manage()
                .window()
                .maximize();
        $(id("createButton")).shouldBe(visible);
        return new ProjectPage();
    }

    public ProjectPage createValidProject(String projectName, String projectCode, String description, String accessType) {
        $(id("createButton")).click();
        $(id("inputTitle")).sendKeys(projectName);
        $(id("inputCode")).clear();
        $(id("inputCode")).sendKeys(projectCode);
        $(id("inputDescription")).sendKeys(description);
        $(id(String.format("%s-access-type",accessType))).click();
        $(xpath("//button[@type='submit']")).click();
        return new ProjectPage();
    }

    public ProjectPage deleteCreatedProject (){
        openProjectPage();
        $(xpath("//i[@class='fa fa-ellipsis-h']")).click();
        $(xpath("//a[@class='text-danger']")).click();
        $(xpath("//button[@type='submit']")).click();
        return new ProjectPage();
    }

    public LoginPage LogOut(){
        $(xpath("//span[@aria-label='Chat']/following::span[1]")).click();
        $(xpath("//span[normalize-space()='Sign out']")).click();
        return new LoginPage();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectAccessType() {
        return projectAccessType;
    }

    public void setProjectAccessType(String projectAccessType) {
        this.projectAccessType = projectAccessType;
    }
}
