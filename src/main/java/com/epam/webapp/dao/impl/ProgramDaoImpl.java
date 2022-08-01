package com.epam.webapp.dao.impl;

import com.epam.webapp.dao.AbstractDao;
import com.epam.webapp.dao.ProgramDao;
import com.epam.webapp.entity.Program;
import com.epam.webapp.entity.ProgramState;
import com.epam.webapp.exception.DaoException;
import com.epam.webapp.mapper.impl.ProgramRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ProgramDaoImpl extends AbstractDao<Program> implements ProgramDao<Program> {

    private static final String SQL_SELECT_FIND_PROGRAM_BY_ORDER_ID =
            "SELECT * FROM `program` WHERE order_id = ?";
    private static final String SQL_SELECT_FIND_PROGRAM_BY_CLIENT_ID =
            """
                    SELECT `program`.id, `program`.order_id, `program`.start,
                    `program`.end, `program`.has_diet, `program`.feedback, `program`.state
                    FROM `program`, `order`
                    WHERE `order`.client_id = ? AND `program`.order_id = `order`.id AND `program`.state != 'finished'
                    """;
    private static final String SQL_SELECT_FIND_PROGRAM_BY_TRAINER_ID =
            "SELECT * FROM `program` where order_id = (SELECT id FROM `order` WHERE trainer_id = ?);";
    private static final String SQL_SELECT_FIND_CLIENT_FINISHED_PROGRAMS =
            """
                    SELECT `program`.id, `program`.order_id, `program`.start,
                    `program`.end, `program`.has_diet, `program`.feedback, `program`.state
                    FROM `program`, `order`
                    WHERE `order`.client_id = ? AND `program`.order_id = `order`.id AND `program`.state = 'finished'
                    """;
    private static final String SQL_SELECT_FIND_FINISHED_PROGRAM_BY_ID =
            "SELECT * FROM `program` WHERE id = ?";
    private static final String SQL_SELECT_FIND_PROCESS_PROGRAM_BY_CLIENT_ID =
            """
                    SELECT * FROM program
                    WHERE order_id = (SELECT id FROM `order` WHERE client_id = ?)
                    AND state = 'process'
                    """;
    private static final String SQL_UPDATE_FEEDBACK =
            """
                    UPDATE `program`
                    SET `feedback` = ?
                    WHERE `id` = ?
                    """;
    private static final String SQL_UPDATE_STATE =
            """
                    UPDATE `program`
                    SET `state` = ?
                    WHERE `id` = ?
                                      """;

    public ProgramDaoImpl(Connection connection) {
        super(connection, new ProgramRowMapper());
    }

    @Override
    public Optional<Program> findByOrderId(long orderId) throws DaoException {
        return executeForSingleResult(SQL_SELECT_FIND_PROGRAM_BY_ORDER_ID, orderId);
    }

    @Override
    public Optional<Program> findByClientId(long clientId) throws DaoException {
        return executeForSingleResult(SQL_SELECT_FIND_PROGRAM_BY_CLIENT_ID, clientId);
    }

    @Override
    public Optional<Program> findByTrainerId(long trainerId) throws DaoException {
        return executeForSingleResult(SQL_SELECT_FIND_PROGRAM_BY_TRAINER_ID, trainerId);
    }

    public List<Program> findAllPrograms() throws DaoException {
        return findAll();
    }

    @Override
    public Optional<Program> findFinishedProgramByClientId(long clientId) throws DaoException {
        return executeForSingleResult(SQL_SELECT_FIND_FINISHED_PROGRAM_BY_ID, clientId);
    }

    @Override
    public Optional<Program> findProcessProgramByClientId(long clientId) throws DaoException {
        return executeForSingleResult(SQL_SELECT_FIND_PROCESS_PROGRAM_BY_CLIENT_ID,clientId);
    }

    @Override
    public List<Program> findFinishedClientPrograms(long clientId) throws DaoException {
        return executeQuery(SQL_SELECT_FIND_CLIENT_FINISHED_PROGRAMS, clientId);
    }

    @Override
    public void updateFeedback(long programId, String feedback) throws DaoException {
        update(SQL_UPDATE_FEEDBACK, feedback, programId);
    }

    @Override
    public void updateProgramState(long programId, ProgramState state) throws DaoException {
        update(SQL_UPDATE_STATE, state.toString(), programId);
    }

    @Override
    public String getTableName() {
        return Program.TABLE;
    }

}
