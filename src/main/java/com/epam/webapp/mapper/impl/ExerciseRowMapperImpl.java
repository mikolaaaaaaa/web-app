package com.epam.webapp.mapper.impl;

import com.epam.webapp.entity.Exercise;
import com.epam.webapp.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseRowMapperImpl implements RowMapper<Exercise> {

    @Override
    public Exercise map(ResultSet resultSet) throws SQLException {
        long id = Long.parseLong(resultSet.getString("id"));
        String name = resultSet.getString("name");
        return new Exercise(
                id,
                name
        );
    }
}
