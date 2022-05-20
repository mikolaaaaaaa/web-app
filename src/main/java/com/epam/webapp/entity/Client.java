package com.epam.webapp.entity;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client extends Entity {
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String LOGIN = "login";
    public static final String TYPE = "type";
    public static final String GENDER = "gender";
    public static final String BALANCE = "balance";
    public static final String TABLE = "client";
    public static final String STATE = "state";

    private String name;
    private String surname;
    private String login;
    private ClientType type;
    private EntityGender gender;
    private double balance;
    private UserState state;

    public Client(long id, String name, String surname, String login, ClientType type,
                  EntityGender gender,double balance, UserState state) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.type = type;
        this.gender = gender;
        this.balance = balance;
        this.state = state;
    }


}
