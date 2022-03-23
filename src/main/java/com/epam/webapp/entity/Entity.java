package com.epam.webapp.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable, Cloneable {

    protected long id;

    public final static String ID = "id";

    public Entity() {

    }

    public Entity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
