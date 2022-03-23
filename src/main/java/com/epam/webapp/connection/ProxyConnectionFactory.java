package com.epam.webapp.connection;

import com.epam.webapp.util.PropertiesLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ProxyConnectionFactory {
    private static final String PATH = "database.properties";

    public ProxyConnection create() {
        Properties properties = PropertiesLoader.load(PATH);
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ProxyConnection(connection);
    }
}
