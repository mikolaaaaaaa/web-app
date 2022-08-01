package com.epam.webapp.mapper.impl;

import com.epam.webapp.entity.Client;
import com.epam.webapp.entity.ClientType;
import com.epam.webapp.entity.EntityGender;
import com.epam.webapp.entity.UserState;
import com.epam.webapp.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRowMapperImpl implements RowMapper {

    public Client map(ResultSet resultSet) throws SQLException {
        long id = Integer.parseInt(resultSet.getString(Client.ID));
        String name = resultSet.getString(Client.NAME);
        String surname = resultSet.getString(Client.SURNAME);
        String login = resultSet.getString(Client.LOGIN);
        String type = resultSet.getString(Client.TYPE);
        ClientType clientType = ClientType.valueOf(type.toUpperCase());
        String gender = resultSet.getString(Client.GENDER);
        EntityGender entityGender = EntityGender.valueOf(gender.toUpperCase());
        double balance = Double.parseDouble(resultSet.getString(Client.BALANCE));
        String state = resultSet.getString(Client.STATE);
        UserState userState = UserState.valueOf(state.toUpperCase());
        return new Client(
                id,
                name,
                surname,
                login,
                clientType,
                entityGender,
                balance,
                userState
        );
    }
}
