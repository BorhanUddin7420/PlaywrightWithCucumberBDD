package com.pwc.hooks;

import com.microsoft.playwright.Page;
import com.qa.managers.DriverFactory;
import com.qa.util.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
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

    @BeforeAll
    public static void init_ExtentReportDir() {
        try {
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/report/"));
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/report/screenshots/"));
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/report/SparkReportHTML/"));
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/report/Json/"));
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/report/Xml/"));
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/report/SparkReportPDF/"));
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/report/Html/"));
            //delete all failed scenario screenshot from screenshots folder
            FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "/report/screenshots/"));
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    @Before(order = 0)
    public void getProperty() {
        ConfigReader configReader = new ConfigReader();
        props = configReader.init_browser();
    }

    @Before(order = 1)
    public void lunchBrowser() {
        DriverFactory driverFactory = new DriverFactory();
        page = driverFactory.initBrowser(props);
    }

    @After(order = 0)
    public void tearDown() {
        page.close();
    }

    @After(order = 1)
    public void takeFailedScreenshot(Scenario sc) {
        if (sc.isFailed()) {
            String screenshotName = sc.getName().replaceAll("", "");
            byte[] sourcePath = page.screenshot();
            sc.attach(sourcePath, "image/png", screenshotName);
        }
    }


}
