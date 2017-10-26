package com.marko.model;

import javax.persistence.*;

/**
 * Created by msav on 7/31/2017.
 */
@Entity
public class Stakeholder {

    @Id
    @GeneratedValue
    private int stakeholderId;

    private String name;

    @Enumerated(EnumType.STRING)
    private StakeholderType type;

    public Stakeholder(String name, StakeholderType type) {
        this.name = name;
        this.type = type;
    }

    public int getStakeholderId() {
        return stakeholderId;
    }

    public void setStakeholderId(int stakeholderId) {
        this.stakeholderId = stakeholderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StakeholderType getType() {
        return type;
    }

    public void setType(StakeholderType type) {
        this.type = type;
    }
}
