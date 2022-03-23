package com.epam.webapp.entity;

import java.util.Objects;

public class Exercise extends Entity {
    private String name;

    public Exercise(long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return Objects.equals(name, exercise.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Exercise{")
                .append("id=").append(id)
                .append(", name='").append(name).append('\'')
                .append('}')
                .toString();
    }
}
