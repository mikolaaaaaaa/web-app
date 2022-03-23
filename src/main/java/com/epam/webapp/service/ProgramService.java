package com.epam.webapp.service;

import com.epam.webapp.dao.DaoHelper;
import com.epam.webapp.dao.DaoHelperFactory;
import com.epam.webapp.dao.ProgramDao;
import com.epam.webapp.dao.impl.ExerciseDaoImpl;
import com.epam.webapp.dao.impl.ProgramDaoImpl;
import com.epam.webapp.entity.Client;
import com.epam.webapp.entity.Entity;
import com.epam.webapp.entity.Exercise;
import com.epam.webapp.entity.Program;
import com.epam.webapp.exception.DaoException;
import com.epam.webapp.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProgramService {

    private static final String SQL_SELECT_FIND_PROGRAM_BY_CLIENT_ID =
            "select * from `program` where order_id = (select id from `order` where client_id = '1')";

    public Optional<Program> showClientProgram(long clientId) throws ServiceException {
        Optional<Program> program = null;
        try(DaoHelper daoHelper = DaoHelperFactory.create()) {
            ProgramDaoImpl programDao = daoHelper.createProgramDao();
            program = programDao.findProgramByClientId(clientId);
            ExerciseDaoImpl exerciseDao = daoHelper.createExerciseDao();
            if (program.isPresent()) {
                List<Exercise> exerciseList = exerciseDao.findExercisesByProgramId(program.get().getId());
                program.get().setExercises(exerciseList);
            }
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        return program;
    }

}
