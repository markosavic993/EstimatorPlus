package com.marko.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by msav on 7/31/2017.
 */
@Entity
public class Project {

    @Id
    @GeneratedValue
    private int projectId;

    private String projectName;

    @Enumerated(EnumType.STRING)
    private ProjectType type;

    @Enumerated(EnumType.STRING)
    private CommunicationProtocol communicationProtocol;

    @OneToMany
    @JoinColumn(name = "stakeholder")
    private List<Stakeholder> stakeholders;

    private String domain;

    @Enumerated(EnumType.STRING)
    private UserInterfaceImpact uiImpact;

    private boolean webtestNeeded;
    private boolean integrationTestNeeded;
    private boolean javascriptTestNeeded;
    private boolean unitTestsNeeded;

    @OneToMany
    @JoinColumn(name = "feature")
    private List<Feature> features;

    @OneToMany
    @JoinColumn(name = "technology")
    private List<Technology> requestedTechnologies;

    @Enumerated(EnumType.STRING)
    private RefactoringLevel refactoringLevel;

    public Project(String projectName, ProjectType type, CommunicationProtocol communicationProtocol, List<Stakeholder> stakeholders, String domain, UserInterfaceImpact uiImpact, boolean webtestNeeded, boolean integrationTestNeeded, boolean javascriptTestNeeded, boolean unitTestsNeeded, List<Feature> features, List<Technology> requestedTechnologies, RefactoringLevel refactoringLevel) {
        this.projectName = projectName;
        this.type = type;
        this.communicationProtocol = communicationProtocol;
        this.stakeholders = stakeholders;
        this.domain = domain;
        this.uiImpact = uiImpact;
        this.webtestNeeded = webtestNeeded;
        this.integrationTestNeeded = integrationTestNeeded;
        this.javascriptTestNeeded = javascriptTestNeeded;
        this.unitTestsNeeded = unitTestsNeeded;
        this.features = features;
        this.requestedTechnologies = requestedTechnologies;
        this.refactoringLevel = refactoringLevel;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ProjectType getType() {
        return type;
    }

    public void setType(ProjectType type) {
        this.type = type;
    }

    public CommunicationProtocol getCommunicationProtocol() {
        return communicationProtocol;
    }

    public void setCommunicationProtocol(CommunicationProtocol communicationProtocol) {
        this.communicationProtocol = communicationProtocol;
    }

    public List<Stakeholder> getStakeholders() {
        return stakeholders;
    }

    public void setStakeholders(List<Stakeholder> stakeholders) {
        this.stakeholders = stakeholders;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public UserInterfaceImpact getUiImpact() {
        return uiImpact;
    }

    public void setUiImpact(UserInterfaceImpact uiImpact) {
        this.uiImpact = uiImpact;
    }

    public boolean isWebtestNeeded() {
        return webtestNeeded;
    }

    public void setWebtestNeeded(boolean webtestNeeded) {
        this.webtestNeeded = webtestNeeded;
    }

    public boolean isIntegrationTestNeeded() {
        return integrationTestNeeded;
    }

    public void setIntegrationTestNeeded(boolean integrationTestNeeded) {
        this.integrationTestNeeded = integrationTestNeeded;
    }

    public boolean isJavascriptTestNeeded() {
        return javascriptTestNeeded;
    }

    public void setJavascriptTestNeeded(boolean javascriptTestNeeded) {
        this.javascriptTestNeeded = javascriptTestNeeded;
    }

    public boolean isUnitTestsNeeded() {
        return unitTestsNeeded;
    }

    public void setUnitTestsNeeded(boolean unitTestsNeeded) {
        this.unitTestsNeeded = unitTestsNeeded;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<Technology> getRequestedTechnologies() {
        return requestedTechnologies;
    }

    public void setRequestedTechnologies(List<Technology> requestedTechnologies) {
        this.requestedTechnologies = requestedTechnologies;
    }

    public RefactoringLevel getRefactoringLevel() {
        return refactoringLevel;
    }

    public void setRefactoringLevel(RefactoringLevel refactoringLevel) {
        this.refactoringLevel = refactoringLevel;
    }
}
