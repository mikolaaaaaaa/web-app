package com.epam.webapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class Entity implements Serializable{

    protected long id;

    public static final String ID = "id";

}
