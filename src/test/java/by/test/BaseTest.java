package by.test;

import by.example.utils.PropertiesLoader;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public String user;
    public String password;

    @BeforeMethod(description = "Opening browser")
    public void setUp() {
        user = System.getProperty("user", String.valueOf(PropertiesLoader.loadProperties()));
        password = System.getProperty("password", String.valueOf(PropertiesLoader.loadProperties()));

        Configuration.baseUrl = "https://app.qase.io/";
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

