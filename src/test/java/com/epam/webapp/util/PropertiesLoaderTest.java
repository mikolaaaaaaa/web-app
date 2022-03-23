package com.epam.webapp.util;

import org.junit.jupiter.api.Test;

import java.util.Properties;

public class PropertiesLoaderTest {
    private static final PropertiesLoader propertiesLoader = new PropertiesLoader();

    @Test
    public void testLoadShouldReturnPropertiesFromCorrectPath() {
        Properties properties = propertiesLoader.load("database.properties");
        String url = properties.getProperty("password");
        System.out.println(url);
    }
}
