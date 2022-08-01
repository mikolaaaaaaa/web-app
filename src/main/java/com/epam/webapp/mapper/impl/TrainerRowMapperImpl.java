package com.epam.webapp.mapper.impl;

import com.epam.webapp.entity.*;
import com.epam.webapp.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainerRowMapperImpl implements RowMapper<Trainer> {

    @Override
    public Trainer map(ResultSet resultSet) throws SQLException {
        long id = Integer.parseInt(resultSet.getString(Trainer.ID));
        String name = resultSet.getString(Trainer.NAME);
        String surname = resultSet.getString(Trainer.SURNAME);
        String login = resultSet.getString(Trainer.LOGIN);
        String qualification = resultSet.getString(Trainer.QUALIFICATION);
        TrainerQualification trainerQualification = TrainerQualification.valueOf(qualification.toUpperCase());
        String gender = resultSet.getString(Client.GENDER);
        EntityGender entityGender = EntityGender.valueOf(gender.toUpperCase());
        String state = resultSet.getString(Client.STATE);
        UserState userState = UserState.valueOf(state.toUpperCase());

        return new Trainer(
                id,
                name,
                surname,
                login,
                trainerQualification,
                entityGender,
                userState
        );
    }
}
