package com.alvachien.model;

import java.util.Objects;

public class Actor {
    private String lastName;
    private String firstName;

    public Actor(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(lastName);
        hash = 67 * hash + Objects.hashCode(firstName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Actor other = (Actor)obj;
        if (!Objects.equals(this.lastName, other.lastName))
            return false;

        return Objects.equals(this.firstName, other.firstName);
    }

    @Override
    public String toString() {
        return "Actor{lastName = " + lastName + ", firstName = " + firstName + "}";
    }
}
