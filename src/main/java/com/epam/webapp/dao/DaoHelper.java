package com.epam.webapp.dao;

import com.epam.webapp.connection.ProxyConnection;
import com.epam.webapp.dao.impl.ClientDaoImpl;
import com.epam.webapp.dao.impl.ExerciseDaoImpl;
import com.epam.webapp.dao.impl.ProgramDaoImpl;
import com.epam.webapp.dao.impl.TrainerDaoImpl;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {
    private final ProxyConnection connection;

    public DaoHelper(ProxyConnection connection) {
       this.connection = connection;
    }

    public ClientDaoImpl createClientDao() {
        return new ClientDaoImpl(connection);
    }

    public TrainerDaoImpl createTrainerDao() {
        return new TrainerDaoImpl(connection);
    }

    public ProgramDaoImpl createProgramDao() {
        return new ProgramDaoImpl(connection);
    }

    public ExerciseDaoImpl createExerciseDao() {
        return new ExerciseDaoImpl(connection);
    }

    @Override
    public void close() throws SQLException {
      connection.close();
    }
}
