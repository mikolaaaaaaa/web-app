package com.epam.webapp.dto;

import com.epam.webapp.entity.ClientType;
import com.epam.webapp.entity.EntityGender;
import com.epam.webapp.entity.UserState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private String name;
    private String surname;
    private String login;
    private String password;
    private ClientType type;
    private EntityGender gender;
    private double balance;
    private UserState state;
}
