package com.marko.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by msav on 7/31/2017.
 */
@Entity
public class Technology {

    @Id
    @GeneratedValue
    private int technologyId;

    private String name;
    private Popularity popularity;

    public Technology() {
    }

    public Technology(String name, Popularity popularity) {
        this.name = name;
        this.popularity = popularity;
    }

    public int getTechnologyId() {
        return technologyId;
    }

    public void setTechnologyId(int technologyId) {
        this.technologyId = technologyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Popularity getPopularity() {
        return popularity;
    }

    public void setPopularity(Popularity popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
