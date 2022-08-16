package com.qa.util;

import java.io.*;
import java.util.Properties;

public class ConfigReader {
    private Properties props;

    public Properties init_browser() {
        props = new Properties();
        //Get the browser, defaulting to local if none is specified.
        String browser = System.getProperty("browser", "chrome");
        String browserFilePath = "./src/test/resources/config/browser/" + browser + ".properties";

        try {
            FileInputStream ip = new FileInputStream(browserFilePath);
            props.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public ConfigReader() {
        //Get the environment, defaulting to local if none is specified.
        String environment = System.getProperty("env", "local");
        String envFilePath = "./src/test/resources/config/env/" + environment + ".properties";

        BufferedReader env;
        try {
            env = new BufferedReader(new FileReader(envFilePath));
            props = new Properties();
            try {
                props.load(env);
                env.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(environment + " environment config properties not found at " + envFilePath);
        }
    }

    public String getBaseUrl() {
        String url = props.getProperty("base_url");
        if (url != null) return url;
        else
            throw new RuntimeException("Application Url not specified in the properties file for the Key:url");
    }
}
