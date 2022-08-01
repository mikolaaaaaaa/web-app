package com.epam.webapp.dao;

import com.epam.webapp.entity.Exercise;
import com.epam.webapp.exception.DaoException;

import java.util.List;

public interface ExerciseDao extends Dao<Exercise> {
    public List<Exercise> findExercisesByProgramId(long programId) throws DaoException;
}
