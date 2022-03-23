package com.epam.webapp.dao.impl;

import com.epam.webapp.dao.AbstractDao;
import com.epam.webapp.dao.ProgramDao;
import com.epam.webapp.entity.Entity;
import com.epam.webapp.entity.Exercise;
import com.epam.webapp.entity.Program;
import com.epam.webapp.exception.DaoException;
import com.epam.webapp.mapper.impl.ProgramRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ProgramDaoImpl extends AbstractDao<Program> implements ProgramDao<Program> {

    private static final String SQL_SELECT_FIND_PROGRAM_BY_ORDER_ID =
            "select * from `program` where order_id = ?";
    private static final String SQL_SELECT_FIND_PROGRAM_BY_CLIENT_ID =
            "select * from `program` where order_id = (select id from `order` where client_id = ?)";
    private static final String SQL_SELECT_FIND_PROGRAM_BY_TRAINER_ID =
            "select * from `program` where order_id = (select id from `order` where trainer_id = ?);";

    public ProgramDaoImpl(Connection connection) {
        super(connection, new ProgramRowMapper());
    }

    @Override
    public String getTableName() {
        return "program";
    }

    @Override
    public Optional<Program> findProgramByOrderId(long orderId) throws DaoException {
        return executeForSingleResult(SQL_SELECT_FIND_PROGRAM_BY_ORDER_ID, orderId);
    }

    @Override
    public Optional<Program> findProgramByClientId(long clientId) throws DaoException {
        return executeForSingleResult(SQL_SELECT_FIND_PROGRAM_BY_CLIENT_ID,clientId);
    }

    @Override
    public Optional<Program> findProgramByTrainerId(long trainerId) throws DaoException {
        return executeForSingleResult(SQL_SELECT_FIND_PROGRAM_BY_TRAINER_ID,trainerId);
    }

}
