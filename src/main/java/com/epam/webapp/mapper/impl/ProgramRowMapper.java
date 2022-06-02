package com.epam.webapp.mapper.impl;

import com.epam.webapp.entity.Entity;
import com.epam.webapp.entity.Program;
import com.epam.webapp.entity.ProgramState;
import com.epam.webapp.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgramRowMapper implements RowMapper<Program> {

    @Override
    public Program map(ResultSet resultSet) throws SQLException {
        int id = Integer.parseInt(resultSet.getString(Program.ID));
        int orderId = Integer.parseInt(resultSet.getString(Program.ORDER_ID));
        String startDate = resultSet.getString(Program.START);
        String endDate = resultSet.getString(Program.END);
        boolean hasDiet = resultSet.getBoolean(Program.HAS_DIET);
        String feedback = resultSet.getString(Program.FEEDBACK);
        ProgramState programState = ProgramState.valueOf(resultSet.getString(Program.STATE).toUpperCase());

        return new Program(id,
                orderId,
                startDate,
                endDate,
                hasDiet,
                feedback,
                programState
        );
    }

}
