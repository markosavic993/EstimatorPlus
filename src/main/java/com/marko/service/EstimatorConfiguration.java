package com.marko.service;

/**
 * Created by msav on 10/28/2017.
 */
public class EstimatorConfiguration {
    private boolean withExplanations;
    private EstimationType estimationType;

    public EstimatorConfiguration() {
    }

    public EstimatorConfiguration(boolean withExplanations, EstimationType estimationType) {
        this.withExplanations = withExplanations;
        this.estimationType = estimationType;
    }

    public boolean isWithExplanations() {
        return withExplanations;
    }

    public void setWithExplanations(boolean withExplanations) {
        this.withExplanations = withExplanations;
    }

    public EstimationType getEstimationType() {
        return estimationType;
    }

    public void setEstimationType(EstimationType estimationType) {
        this.estimationType = estimationType;
    }
}
