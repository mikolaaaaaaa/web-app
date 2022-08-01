package com.epam.webapp.dao;

import com.epam.webapp.connection.ConnectionPool;

public class DaoHelperFactory {
    public static DaoHelper create() {
        return new DaoHelper(ConnectionPool
                .getInstance()
                .getConnection()
        );
    }
}
