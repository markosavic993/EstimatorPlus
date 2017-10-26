package com.marko.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Created by msav on 7/31/2017.
 */
@Entity(name = "team")
public class Team {

    @Id
    @GeneratedValue
    private int teamId;

    private String teamName;

    @OneToMany
    @JoinColumn(name = "member")
    private List<TeamMember> members;

    @OneToMany
    @JoinColumn(name = "project")
    private List<Project> projects;

    private boolean distributed;

    @OneToMany
    @JoinColumn(name = "scrum_tool")
    private List<ScrumTools> scrumTools;

    @OneToMany
    @JoinColumn(name = "technology")
    private List<Technology> technologies;

    @OneToMany
    @JoinColumn(name = "knownFeature")
    private List<Feature> knownFeatures;

    @OneToMany
    @JoinColumn(name = "stakeholder")
    private List<Stakeholder> knownStakeholders;

    @ElementCollection
    private List<String> familiarDomains;


    public Team(String teamName, List<TeamMember> members, List<Project> projects, boolean distributed, List<ScrumTools> scrumTools, List<Technology> technologies, List<Feature> knownFeatures, List<Stakeholder> knownStakeholders, List<String> familiarDomains) {
        this.teamName = teamName;
        this.members = members;
        this.projects = projects;
        this.distributed = distributed;
        this.scrumTools = scrumTools;
        this.technologies = technologies;
        this.knownFeatures = knownFeatures;
        this.knownStakeholders = knownStakeholders;
        this.familiarDomains = familiarDomains;
    }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Team() {
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<TeamMember> getMembers() {
        return members;
    }

    public void setMembers(List<TeamMember> members) {
        this.members = members;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public boolean isDistributed() {
        return distributed;
    }

    public void setDistributed(boolean distributed) {
        this.distributed = distributed;
    }

    public List<ScrumTools> getScrumTools() {
        return scrumTools;
    }

    public void setScrumTools(List<ScrumTools> scrumTools) {
        this.scrumTools = scrumTools;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }

    public List<Feature> getKnownFeatures() {
        return knownFeatures;
    }

    public void setKnownFeatures(List<Feature> knownFeatures) {
        this.knownFeatures = knownFeatures;
    }

    public List<Stakeholder> getKnownStakeholders() {
        return knownStakeholders;
    }

    public void setKnownStakeholders(List<Stakeholder> knownStakeholders) {
        this.knownStakeholders = knownStakeholders;
    }

    public List<String> getFamiliarDomains() {
        return familiarDomains;
    }

    public void setFamiliarDomains(List<String> familiarDomains) {
        this.familiarDomains = familiarDomains;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return getTeamId() == team.getTeamId() &&
                Objects.equals(getTeamName(), team.getTeamName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTeamId(), getTeamName());
    }
}
