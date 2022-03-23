package com.epam.webapp.mapper;

import com.epam.webapp.entity.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Entity> {
    public T map(ResultSet resultSet) throws SQLException;
}
