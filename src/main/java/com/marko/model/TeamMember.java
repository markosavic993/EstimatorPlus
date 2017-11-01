package com.marko.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by msav on 7/31/2017.
 */
@Entity
public class TeamMember {

    @Id
    private String email;
    private String name;

    @Enumerated(EnumType.STRING)
    private SeniorityLevel seniorityLevel;

    public TeamMember() {
    }

    public TeamMember(String email, String name, SeniorityLevel seniorityLevel) {
        this.email = email;
        this.name = name;
        this.seniorityLevel = seniorityLevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SeniorityLevel getSeniorityLevel() {
        return seniorityLevel;
    }

    public void setSeniorityLevel(SeniorityLevel seniorityLevel) {
        this.seniorityLevel = seniorityLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamMember that = (TeamMember) o;
        return Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getName(), that.getName()) &&
                getSeniorityLevel() == that.getSeniorityLevel();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getName(), getSeniorityLevel());
    }
}
