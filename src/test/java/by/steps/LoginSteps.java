package by.steps;

import by.example.pages.LoginPage;
import by.example.pages.ProjectPage;
import by.test.BaseTest;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.testng.ScreenShooter;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class LoginSteps extends BaseTest {
    @Given("open login page and log in with next valid params:")
    public void openLoginPageAndLogInWithNextParams(DataTable validCredentials) {
        ScreenShooter.captureSuccessfulTests = true;
        Map<String, String> params = validCredentials.asMap(String.class, String.class);
        new LoginPage()
                .open()
                .login(params.get("username"),params.get("password"));

        $(id("createButton")).shouldHave(text("Create new project"));
        assertThat($(id("createButton"))
                .getText())
                .isNotNull()
                .isEqualTo("Create new project");
    }

    @Given("open login page and log in with next not valid params:")
    public void openLoginPageAndLogInWithNextNotValidParams(DataTable invalidCredentials) {
        ScreenShooter.captureSuccessfulTests = true;
        Map<String, String> params = invalidCredentials.asMap(String.class, String.class);
        new LoginPage()
                .open()
                .login(params.get("username"),params.get("password"));

        assertThat($(xpath("//div[@class='form-control-feedback']"))
                .getText())
                .isNotNull()
                .isEqualTo("These credentials do not match our records.");
    }

    @And("logOut")
    public void logout() {
        new ProjectPage()
                .openProjectPage()
                .LogOut();
    }


}
