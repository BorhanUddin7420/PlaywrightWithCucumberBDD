package com.qa.managers;

import com.microsoft.playwright.*;

import java.util.List;
import java.util.Properties;

public class DriverFactory {

    Playwright playwright;

    private static final ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    private static final ThreadLocal<Page> tlPage = new ThreadLocal<>();


    public static Playwright getPlayWright() {
        return tlPlaywright.get();
    }

    public static Browser getBrowser() {
        return tlBrowser.get();
    }

    public static BrowserContext getBrowserContext() {
        return tlBrowserContext.get();
    }

    public static Page getPage() {
        return tlPage.get();
    }


    public Page initBrowser(Properties props) {
        String browserName = props.getProperty("browser").trim();
        boolean browserState = Boolean.parseBoolean(props.getProperty("headless").trim());
        System.out.println("Browser: " + browserName);
        System.out.println("Browser start in headless = " + browserState);
        if (browserState) {
            System.out.println("Browser started headless");
        } else {
            System.out.println("Browser started non-headless");
        }

        tlPlaywright.set(Playwright.create());

        switch (browserName.toLowerCase()) {
            case "chromium":
                tlBrowser.set(playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(browserState).setArgs(List.of("--start-maximized"))));
                break;
            case "firefox":
                tlBrowser.set(getPlayWright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(browserState).setArgs(List.of("--start-maximized"))));
                break;
            case "safari":
                tlBrowser.set(getPlayWright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(browserState).setArgs(List.of("--start-maximized"))));
                break;
            case "chrome":
                tlBrowser.set(getPlayWright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(browserState).setArgs(List.of("--start-maximized"))));
                break;
            default:
                System.out.println("There is no browser found with your provided browser name :" + browserName);
                break;
        }

        tlBrowserContext.set(getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(null)));
        tlPage.set(getBrowserContext().newPage());
        return getPage();


    }
}
