package com.marko.web;

import com.marko.model.Project;
import com.marko.model.context.EstimationContext;

import java.util.List;

/**
 * Created by msav on 10/30/2017.
 */
public class RefinementValueObject {

    private List<EstimationAtendee> attendees;
    private String estimation;
    private Project project;
    private String explanationText;
    private EstimationContext context;

    public RefinementValueObject(List<EstimationAtendee> attendees, String estimation, Project project, String explanationText, EstimationContext context) {
        this.attendees = attendees;
        this.estimation = estimation;
        this.project = project;
        this.explanationText = explanationText;
        this.context = context;
    }

    public List<EstimationAtendee> getAttendees() {
        return attendees;
    }

    public String getEstimation() {
        return estimation;
    }

    public Project getProject() {
        return project;
    }

    public String getExplanationText() {
        return explanationText;
    }

    public EstimationContext getContext() {
        return context;
    }

    public void setAttendees(List<EstimationAtendee> attendees) {
        this.attendees = attendees;
    }

    public void setEstimation(String estimation) {
        this.estimation = estimation;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setExplanationText(String explanationText) {
        this.explanationText = explanationText;
    }

    public void setContext(EstimationContext context) {
        this.context = context;
    }
}
