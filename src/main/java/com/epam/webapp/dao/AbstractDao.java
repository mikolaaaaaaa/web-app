package com.epam.webapp.dao;

import com.epam.webapp.entity.Entity;
import com.epam.webapp.exception.DaoException;
import com.epam.webapp.mapper.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Entity> implements Dao<T> {

    private final RowMapper<T> mapper;
    private final Connection connection;

    private static final String FIND_ALL = "select * from ";
    private static final String WHERE = "where";

    public AbstractDao(Connection connection, RowMapper<T> rowMapper) {
        this.connection = connection;
        this.mapper = rowMapper;
    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        List<T> entities = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = createStatement(query, params);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return entities;
    }

    protected void executeUpdate(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected Optional<T> executeForSingleResult(String query, Object... params) throws DaoException {
        List<T> items = executeQuery(query, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else if (items.size() > 1) {
            throw new IllegalArgumentException("More than one record found");
        } else {
            return Optional.empty();
        }
    }

    protected PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            preparedStatement.setObject(i, params[i - 1]);
        }
        return preparedStatement;
    }

    @Override
    public Optional<T> findById(Long id) throws DaoException {
        return executeForSingleResult(FIND_ALL + getTableName() + WHERE + Long.toString(id), id);
    }

    @Override
    public List<T> findAll() throws DaoException {
        return executeQuery(FIND_ALL + getTableName());
    }

    @Override
    public void save(T item) throws DaoException {

    }

    @Override
    public void deleteById(Long id) throws DaoException {

    }

    public abstract String getTableName();
}

