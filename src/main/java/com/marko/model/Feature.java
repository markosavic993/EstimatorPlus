package com.marko.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Created by msav on 5/17/2017.
 */
@Entity
public class Feature {

    @Id
    @GeneratedValue
    private int featureId;

    private String name;
    private String description;

    public Feature() {
    }

    public Feature(String name, String description) {
        this.name = name;
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feature feature = (Feature) o;
        return Objects.equals(getName(), feature.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }
}
