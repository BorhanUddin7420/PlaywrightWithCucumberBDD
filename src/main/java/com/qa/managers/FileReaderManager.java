package com.qa.managers;


import com.qa.util.ConfigReader;

public class FileReaderManager {

    private static final FileReaderManager fileReaderManager = new FileReaderManager();
    private static ConfigReader configReader;

    private FileReaderManager() {
        //implement later
    }

    public static FileReaderManager getInstance() {

        return fileReaderManager;
    }

    public static void setConfigReader(ConfigReader configReader) {
        FileReaderManager.configReader = configReader;
    }

    public ConfigReader getConfigReader() {
        return (configReader == null) ? new ConfigReader() : configReader;
    }
}
