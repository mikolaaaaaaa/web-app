package com.epam.webapp.entity;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trainer extends Entity {
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String LOGIN = "login";
    public static final String QUALIFICATION = "qualification";
    public static final String GENDER = "gender";
    public static final String TABLE = "trainer";
    public static final String STATE = "state";

    private String name;
    private String surname;
    private String login;
    private TrainerQualification qualification;
    private EntityGender gender;
    private UserState state;

    public Trainer(Long id, String name, String surname, String login,
                   TrainerQualification qualification, EntityGender gender,UserState state) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.qualification = qualification;
        this.gender = gender;
        this.state = state;
    }

}
