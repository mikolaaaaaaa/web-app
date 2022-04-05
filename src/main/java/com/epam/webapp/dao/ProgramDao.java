package com.epam.webapp.dao;

import com.epam.webapp.entity.Entity;
import com.epam.webapp.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface ProgramDao<T extends Entity> extends Dao<T> {

    public Optional<T> findProgramByOrderId(long orderId) throws DaoException;
    public Optional<T> findProgramByClientId(long clientId) throws DaoException;
    public Optional<T> findProgramByTrainerId(long trainerId) throws DaoException;
    public List<T> findAllPrograms() throws DaoException;
    public Optional<T> findFinishedProgramById(long programId) throws DaoException;
    public List<T> findClientFinishedPrograms(long clientId) throws DaoException;

}
