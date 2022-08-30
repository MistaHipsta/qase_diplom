package by.example.pages;

import by.example.utils.PropertiesLoader;
import com.codeborne.selenide.Selenide;

import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.id;

public class LoginPage {

    public LoginPage open() {
        Selenide.open("/login");
        return new LoginPage();
    }

    public ProjectPage login(String name, String password){
        $(id("inputEmail")).sendKeys(name);
        $(id("inputPassword")).sendKeys(password);
        $(id("btnLogin")).click();
        return new ProjectPage();
    }

    public ProjectPage loginValidUser() {
        Properties properties = PropertiesLoader.loadProperties();
        new LoginPage()
                .open()
                .login(properties.getProperty("username"), properties.getProperty("password"));
        assertThat($(id("createButton")).getText()).isEqualTo("Create new project");
        return new ProjectPage();
    }
}
