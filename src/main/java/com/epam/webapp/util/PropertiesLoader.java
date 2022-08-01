package com.epam.webapp.util;

import com.epam.webapp.connection.ConnectionPool;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertiesLoader {
    public static Properties load(String path) {
        Properties properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(ConnectionPool.
                    class.
                    getClassLoader().
                    getResourceAsStream(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
