package com.epam.webapp.service;

import com.epam.webapp.dao.DaoHelper;
import com.epam.webapp.dao.DaoHelperFactory;
import com.epam.webapp.dao.impl.ExerciseDaoImpl;
import com.epam.webapp.dao.impl.ProgramDaoImpl;
import com.epam.webapp.entity.Exercise;
import com.epam.webapp.entity.Program;
import com.epam.webapp.entity.ProgramState;
import com.epam.webapp.exception.DaoException;
import com.epam.webapp.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgramService {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String SQL_SELECT_FIND_PROGRAM_BY_CLIENT_ID =
            "select * from `program` where order_id = (select id from `order` where client_id = '1')";

    public Optional<Program> showProcessProgram(long clientId) throws ServiceException {
        Optional<Program> program = null;
        try (DaoHelper daoHelper = DaoHelperFactory.create()) {
            ProgramDaoImpl programDao = daoHelper.createProgramDao();
            program = programDao.findProcessProgramByClientId(clientId);
            if (program.isPresent()) {
                program.get().setExercises(showExercises(program.get().getId()));
            } else {
                LOGGER.info("client program not found by id {}", clientId);
            }
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        return program;
    }

    public List<Program> showFinishedClientPrograms(long clientId) throws ServiceException {
        List<Program> programs = new ArrayList<>();
        try (DaoHelper daoHelper = DaoHelperFactory.create()) {
            ProgramDaoImpl programDao = daoHelper.createProgramDao();
            programs = programDao.findFinishedClientPrograms(clientId);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        return programs;
    }

    public Optional<Program> showFinishedProgram(long programId) throws ServiceException {
        try (DaoHelper daoHelper = DaoHelperFactory.create()) {
            ProgramDaoImpl programDao = daoHelper.createProgramDao();
            Optional<Program> program = programDao.findFinishedProgramByClientId(programId);
            if (program.isPresent()) {
                program.get().setExercises(showExercises(programId));
            }
            return program;
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Exercise> showExercises(long programId) throws ServiceException {
        List<Exercise> exercises;
        try (DaoHelper daoHelper = DaoHelperFactory.create()) {
            ExerciseDaoImpl exerciseDao = daoHelper.createExerciseDao();
            exercises = new ArrayList<>(exerciseDao.findExercisesByProgramId(programId));
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        return exercises;
    }

    public void updateClientFeedback(Program program, String feedback) throws ServiceException {
        LOGGER.info("feedback  - {}", feedback);
        program.setFeedback(feedback);
        try (DaoHelper daoHelper = DaoHelperFactory.create()) {
            ProgramDaoImpl programDao = daoHelper.createProgramDao();
            programDao.updateFeedback(program.getId(), feedback);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void updateProgramState(long programId, ProgramState state) throws ServiceException {
        try (DaoHelper daoHelper = DaoHelperFactory.create()) {
            ProgramDaoImpl programDao = daoHelper.createProgramDao();
            programDao.updateProgramState(programId,state);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

}
