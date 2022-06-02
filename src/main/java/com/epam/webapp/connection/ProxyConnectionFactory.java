package com.epam.webapp.connection;

import com.epam.webapp.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ProxyConnectionFactory {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String PATH = "database.properties";

    public ProxyConnection create() {
        Properties properties = PropertiesLoader.load(PATH);
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        LOGGER.info("connection {} is created",connection);
        return new ProxyConnection(connection);
    }
}
