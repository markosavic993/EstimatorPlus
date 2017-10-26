package com.marko.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by msav on 7/31/2017.
 */
@Entity
public class ScrumTools {

    @Id
    @GeneratedValue
    private int scrumToolId;

    private String name;
    private String description;

    public ScrumTools(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getScrumToolId() {
        return scrumToolId;
    }

    public void setScrumToolId(int scrumToolId) {
        this.scrumToolId = scrumToolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
