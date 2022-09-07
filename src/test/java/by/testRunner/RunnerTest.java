package by.testRunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/resources/features",
        glue = "by.steps",
        tags = "@smoke",
        plugin = { "pretty", "html:target/cucumber.html",
                "json:target/cucumber.json"})
public class RunnerTest extends AbstractTestNGCucumberTests {
}