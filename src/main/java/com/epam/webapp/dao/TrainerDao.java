package com.epam.webapp.dao;

import com.epam.webapp.entity.Entity;
import com.epam.webapp.entity.Trainer;
import com.epam.webapp.exception.DaoException;

import java.util.Optional;

public interface TrainerDao<Trainer extends Entity> extends Dao<Trainer> {
    Optional<Trainer> findTrainerByLoginAndPassword(String login,String password) throws DaoException;
    Optional<Trainer> findTrainerByLogin(String login) throws DaoException;
}
