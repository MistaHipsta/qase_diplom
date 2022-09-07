package by.steps.UI;

import by.example.pages.LoginPage;
import by.example.pages.ProjectPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();
    ProjectPage projectPage = new ProjectPage();

    @Given("open login page and log in with next valid params:")
    public void openLoginPageAndLogInWithNextParams(DataTable validCredentials) {


        Map<String, String> params = validCredentials.asMap(String.class, String.class);
        loginPage
                .open()
                .login(params.get("username"), params.get("password"));

        loginPage.getCreateButton().shouldHave(text("Create new project"));
        assertThat(loginPage.getCreateButton()
                .getText())
                .isNotNull()
                .isEqualTo("Create new project");
    }

    @Given("open login page and log in with next not valid params:")
    public void openLoginPageAndLogInWithNextNotValidParams(DataTable invalidCredentials) {

        Map<String, String> params = invalidCredentials.asMap(String.class, String.class);
        loginPage
                .open()
                .login(params.get("username"),params.get("password"));

        assertThat(loginPage.getInformMessage()
                .getText())
                .isNotNull()
                .isEqualTo("These credentials do not match our records.");
    }

    @And("logOut")
    public void logout() {
        projectPage
                .openProjectPage()
                .LogOut();
    }

}
