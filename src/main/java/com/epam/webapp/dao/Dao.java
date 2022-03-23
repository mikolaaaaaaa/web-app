package com.epam.webapp.dao;

import com.epam.webapp.entity.Entity;
import com.epam.webapp.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Entity> {
    Optional<T> findById(Long id) throws DaoException;
    List<T> findAll() throws DaoException;
    void save(T item) throws DaoException;
    void deleteById(Long id) throws DaoException;

}
