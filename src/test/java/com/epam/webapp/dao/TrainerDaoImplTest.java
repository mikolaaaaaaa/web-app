package com.epam.webapp.dao;

import com.epam.webapp.dao.impl.TrainerDaoImpl;
import com.epam.webapp.entity.Trainer;
import com.epam.webapp.exception.DaoException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class TrainerDaoImplTest {
    private static final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    @Test
    public void testFindTrainerByLoginAndPassword() throws SQLException {

        try (DaoHelper daoHelper = DaoHelperFactory.create()) {
            TrainerDaoImpl trainerDao = daoHelper.createTrainerDao();
            List<Trainer> trainers = trainerDao.executeQuery(
                    "select * from trainer where login = ? and password = MD5(?)",
                    "anton",
                    "pass");
            trainers.forEach(System.out::println);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
