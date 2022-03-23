package com.epam.webapp.entity;

import java.util.Objects;

public class Client extends Entity {
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String LOGIN = "login";
    public static final String TYPE = "type";
    public static final String GENDER = "gender";
    public static final String TABLE = "client";

    private String name;
    private String surname;
    private String login;
    private ClientType type;
    private EntityGender gender;

    public Client(long id, String name, String surname, String login, ClientType type, EntityGender gender) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.type = type;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public EntityGender getGender() {
        return gender;
    }

    public void setGender(EntityGender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Client client = (Client) object;
        return Objects.equals(name, client.name) &&
               Objects.equals(surname, client.surname) &&
               Objects.equals(login, client.login) &&
               Objects.equals(type, client.type) &&
               Objects.equals(gender, client.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, login,type, gender);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("User{")
                .append("id=").append(getId())
                .append(", name='").append(name).append('\'')
                .append(", surname='").append(surname).append('\'')
                .append(", login='").append(login).append('\'')
                .append(", type='").append(type).append('\'')
                .append(", gender='").append(gender).append('\'')
                .append('}').toString();
    }
}
