package by.example.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class LoginPage {

    SelenideElement createButton = $(id("createButton"));
    SelenideElement informMessage = $(xpath("//div[@class='form-control-feedback']"));
    SelenideElement emailInput = $(id("inputEmail"));
    SelenideElement passwordInput = $(id("inputPassword"));
    SelenideElement loginButton = $(id("btnLogin"));

    public LoginPage open() {
        Selenide.open("/login");
        getWebDriver()
                .manage()
                .window()
                .maximize();
        return new LoginPage();
    }

    public ProjectPage login(String name, String password){
        emailInput.sendKeys(name);
        passwordInput.sendKeys(password);
        loginButton.click();
        return new ProjectPage();
    }

    public SelenideElement getCreateButton() {
        return createButton;
    }

    public SelenideElement getInformMessage() {
        return informMessage;
    }
}
