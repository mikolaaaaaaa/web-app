package com.epam.webapp.dao;

import com.epam.webapp.entity.Entity;
import com.epam.webapp.entity.ProgramState;
import com.epam.webapp.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface ProgramDao<T extends Entity> extends Dao<T> {

    Optional<T> findByOrderId(long orderId) throws DaoException;

    Optional<T> findByClientId(long clientId) throws DaoException;

    Optional<T> findByTrainerId(long trainerId) throws DaoException;

    List<T> findAllPrograms() throws DaoException;

    Optional<T> findFinishedProgramByClientId(long clientId) throws DaoException;

    Optional<T> findProcessProgramByClientId(long clientId) throws DaoException;

    List<T> findFinishedClientPrograms(long clientId) throws DaoException;

    void updateFeedback(long programId, String feedback) throws DaoException;

    void updateProgramState(long programId, ProgramState state) throws DaoException;

}
