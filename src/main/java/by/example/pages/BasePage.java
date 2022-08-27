package by.example.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BasePage {

    @BeforeMethod(description = "Opening browser")
    public void setUp() {

        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.clickViaJs = true;
        Configuration.timeout = 15000;
    }

    @AfterMethod(alwaysRun = true, description = "Closing Browser")
    public void close() {
        Selenide.close();
    }
}
