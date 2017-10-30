package com.marko.web;

import com.marko.model.TeamMember;

/**
 * Created by msav on 10/29/2017.
 */
public class EstimationAtendee {

    private TeamMember teamMember;
    private String avatarPath;
    private String estimation;

    public EstimationAtendee() {
    }

    public EstimationAtendee(TeamMember teamMember, String avatarPath, String estimation) {
        this.teamMember = teamMember;
        this.avatarPath = avatarPath;
        this.estimation = estimation;
    }

    public TeamMember getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(TeamMember teamMember) {
        this.teamMember = teamMember;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getEstimation() {
        return estimation;
    }

    public void setEstimation(String estimation) {
        this.estimation = estimation;
    }
}
