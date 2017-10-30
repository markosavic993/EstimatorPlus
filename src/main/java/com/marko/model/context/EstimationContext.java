package com.marko.model.context;

/**
 * Created by msav on 10/30/2017.
 */
public enum EstimationContext {
    REFINEMENT("refinement"),
    REVEALMENT("revealment");

    private final String contextName;

    EstimationContext(String contextName) {
        this.contextName = contextName;
    }

    public String getContextName() {
        return contextName;
    }
}
