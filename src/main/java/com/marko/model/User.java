package com.marko.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by msav on 7/31/2017.
 */
@Entity
@Table(name = "user")
public class User{

    @Id
    private String username;
    private String password;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teamId")
    private Team team;
    private String email;

    public User(String username, String password, Team team, String email) {
        this.username = username;
        this.password = password;
        this.team = team;
        this.email = email;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }
}
