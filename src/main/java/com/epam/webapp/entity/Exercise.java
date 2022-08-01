package com.epam.webapp.entity;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exercise extends Entity {

    public static final String TABLE = "exercise";

    private String name;

    public Exercise(long id, String name) {
        super(id);
        this.name = name;
    }

}
