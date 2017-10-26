package com.marko.model;

import javax.persistence.*;

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
}
