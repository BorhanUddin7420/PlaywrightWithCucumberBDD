package com.qa.managers;

import com.microsoft.playwright.*;

import java.security.PublicKey;
import java.util.Properties;

public class DriverFactory {

    Playwright playwright;

    private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage = new ThreadLocal<>();


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
        System.out.println("browser Name is = " + browserName);
        System.out.println("Browser start in headless = " + browserState);

        tlPlaywright.set(Playwright.create());

        switch (browserName.toLowerCase()) {
            case "chromium":
                tlBrowser.set(playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(browserState)));
                break;
            case "firefox":
                tlBrowser.set(getPlayWright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(browserState)));
                break;
            case "safari":
                tlBrowser.set(getPlayWright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(browserState)));
                break;
            case "chrome":
                tlBrowser.set(getPlayWright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(browserState)));
                break;
            default:
                System.out.println("There is no browser found with your provided browser name :" + browserName);
                break;
        }

        tlBrowserContext.set(getBrowser().newContext());
        tlPage.set(getBrowserContext().newPage());
        return getPage();


    }


}
