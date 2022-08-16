package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        glue = {"stepdefinitaions", "apphooks"},
        plugin = {"pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "json:report/cucumber-reports/Cucumber.json",
                "junit:report/cucumber-reports/Cucumber.xml"
        }
)

public class loginTestRunner {

}
