package com.epam.webapp.dao.impl;

import com.epam.webapp.dao.AbstractDao;
import com.epam.webapp.dao.ExerciseDao;
import com.epam.webapp.entity.Exercise;
import com.epam.webapp.exception.DaoException;
import com.epam.webapp.mapper.RowMapper;
import com.epam.webapp.mapper.impl.ExerciseRowMapperImpl;

import java.sql.Connection;
import java.util.List;

public class ExerciseDaoImpl extends AbstractDao<Exercise> implements ExerciseDao {

    private static final String SQL_SELECT_FIND_EXERCISE_BY_PROGRAM_ID =
            """
               SELECT * FROM `program_exercise`
               join `exercise` on exercise.id = program_exercise.exercise_id
               where program_id = ?
               """;

    public ExerciseDaoImpl(Connection connection) {
        super(connection, new ExerciseRowMapperImpl());
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public List<Exercise> findExercisesByProgramId(long programId) throws DaoException {
        return executeQuery(SQL_SELECT_FIND_EXERCISE_BY_PROGRAM_ID,programId);
    }
}
