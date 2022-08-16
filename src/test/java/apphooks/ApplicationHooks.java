package apphooks;

import com.microsoft.playwright.Page;
import com.pages.LoginPage;
import com.qa.managers.DriverFactory;
import com.qa.util.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;


public class ApplicationHooks {

    private Page page;
    Properties props;
    protected LoginPage loginPage;

    @Before(order = 0)
    public void init_ExtentReport() {
        try {
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/report/"));
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/report/screenshots/"));
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/report/SparkReportHTML/"));
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/report/SparkReportPDF/"));
            //delete all failed scenario screenshot from screenshots folder
            FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "/report/screenshots/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before(order = 1)
    public void getProperty() {
        ConfigReader configReader = new ConfigReader();
        props = configReader.init_browser();
    }

    @Before(order = 2)
    public void lunchBrowser() {
        DriverFactory driverFactory = new DriverFactory();
        page = driverFactory.initBrowser(props);
        loginPage = new LoginPage(page);
    }

    @After(order = 0)
    public void tearDown() {
        page.close();
    }

    @After(order = 1)
    public void takeFailedScreenshot(Scenario sc) {
        if (sc.isFailed()) {
            String screenshotName = sc.getName().replaceAll("", "_");
            byte[] sourcePath = page.screenshot();
            sc.attach(sourcePath, "image/png", screenshotName);
        }
    }


}
