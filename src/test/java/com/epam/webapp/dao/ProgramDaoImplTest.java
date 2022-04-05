package com.epam.webapp.dao;

import com.epam.webapp.dao.impl.ProgramDaoImpl;
import com.epam.webapp.entity.Program;
import com.epam.webapp.exception.DaoException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProgramDaoImplTest {

    private static final DaoHelperFactory factory = new DaoHelperFactory();

    @Test
    public void testProgramDao() throws SQLException, DaoException {
        List<Program> programs;
        try (DaoHelper daoHelper = DaoHelperFactory.create()){
            ProgramDaoImpl dao = daoHelper.createProgramDao();
            programs = dao.findClientFinishedPrograms(1);
        }
        programs.forEach(System.out::println);
     }

     @Test
    public void testFindProgramByOrderId() {
         Optional<Program> program = null;
         try(DaoHelper daoHelper = DaoHelperFactory.create()) {
             ProgramDaoImpl programDao = daoHelper.createProgramDao();
             program = programDao.findProgramByOrderId(1);
         } catch (SQLException e) {
             e.printStackTrace();
         } catch (DaoException e) {
             e.printStackTrace();
         }
         System.out.println(program.get());
     }

     @Test
    public void testFindFinishedProgram() throws SQLException, DaoException {
        try (DaoHelper daoHelper = DaoHelperFactory.create()){
            ProgramDaoImpl programDao = daoHelper.createProgramDao();
            Optional<Program> program = programDao.findFinishedProgramById(2);
            System.out.println(program.get());
        }
     }
}
