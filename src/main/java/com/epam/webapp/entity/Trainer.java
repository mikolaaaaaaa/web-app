package com.epam.webapp.entity;

import java.util.Objects;

public class Trainer extends Entity {
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String LOGIN = "login";
    public static final String QUALIFICATION = "qualification";
    public static final String GENDER = "gender";
    public static final String TABLE = "trainer";

    private String name;
    private String surname;
    private String login;
    private TrainerQualification qualification;
    private EntityGender gender;

    public Trainer() {

    }

    public Trainer(Long id, String name, String surname, String login,
                   TrainerQualification qualification, EntityGender gender) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.qualification = qualification;
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

    public TrainerQualification getQualification() {
        return qualification;
    }

    public void setQualification(TrainerQualification qualification) {
        this.qualification = qualification;
    }

    public EntityGender getGender() {
        return gender;
    }

    public void setGender(EntityGender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return Objects.equals(name, trainer.name) &&
                Objects.equals(surname, trainer.surname) &&
                Objects.equals(login, trainer.login) &&
                qualification == trainer.qualification &&
                gender == trainer.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, login, qualification, gender);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Trainer{")
                .append("id=").append(id)
                .append(", name='").append(name).append('\'')
                .append(", surname='").append(surname).append('\'')
                .append(", login='").append(login).append('\'')
                .append(", qualification=").append(qualification)
                .append(", gender=").append(gender)
                .append('}')
                .toString();
    }
}
