package com.epam.webapp.connection;

import com.epam.webapp.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Properties;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger();

    private final ProxyConnectionFactory connectionFactory = new ProxyConnectionFactory();

    private static AtomicReference<ConnectionPool> instance = new AtomicReference<>();

    private Deque<ProxyConnection> availableConnections;
    private Deque<ProxyConnection> connectionInUse;

    private static final int DEFAULT_NUMBER_OF_CONNECTION = 10;

    private Semaphore connectionSemaphore;
    private static final Lock instanceLock = new ReentrantLock();
    private static final Lock connectionLock = new ReentrantLock();

    private int numberOfConnections;
    private static final String CONNECTIONS_AMOUNT_KEY = "connections_amount";
    private static final String PROPERTY_PATH = "database.properties";

    private ConnectionPool() {
        availableConnections = new ArrayDeque<>();
        connectionInUse = new ArrayDeque<>();
        Properties properties = PropertiesLoader.load(PROPERTY_PATH);
        try {
            numberOfConnections = Integer.parseInt(properties.getProperty(CONNECTIONS_AMOUNT_KEY));
        } catch (NumberFormatException e) {
            numberOfConnections = DEFAULT_NUMBER_OF_CONNECTION;
            LOGGER.warn("Incorrect number of connections, set default = {}", DEFAULT_NUMBER_OF_CONNECTION);
        }
        createPool();
    }

    public static ConnectionPool getInstance() {
       if (instance.get() == null) {
           instanceLock.lock();
           try {
               instance.compareAndSet(null, new ConnectionPool());
           } finally {
               instanceLock.unlock();
           }
       }
       return instance.get();
    }

    public ProxyConnection getConnection() {
       ProxyConnection connection = null;
       try {
           connectionSemaphore.acquire();
           connectionLock.lock();
           connection = availableConnections.poll();
           connectionInUse.add(connection);
       } catch (InterruptedException e) {
           LOGGER.error("Error in ConnectionPool, getConnection");
       } finally {
          connectionLock.unlock();
       }
       return connection;
    }

    public void returnConnection(ProxyConnection connection) {
        connectionLock.lock();
        try {
            if (connectionInUse.contains(connection)) {
                connectionInUse.removeFirstOccurrence(connection);
                availableConnections.add(connection);
            }
        } finally {
            connectionLock.unlock();
            connectionSemaphore.release();
        }
    }

    private void createPool() {
        connectionSemaphore = new Semaphore(numberOfConnections);
        ProxyConnectionFactory connectionFactory = new ProxyConnectionFactory();
        for (int i = 0; i < numberOfConnections; i++) {
            availableConnections.add(connectionFactory.create());
        }
    }

    public void closeAll(){
        for(int i = 0; i < numberOfConnections; i++) {
            ProxyConnection connection = availableConnections.poll();
            if (connection != null) {
                LOGGER.info("connection {} really close", connection);
                connection.reallyClose();
            }
        }
    }

}
