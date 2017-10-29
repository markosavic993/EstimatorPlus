package com.marko.web;

import com.marko.model.TeamMember;

import java.util.List;

/**
 * Created by msav on 10/29/2017.
 */
public class InviteesValueObject {

    private List<TeamMember> invitees;

    public InviteesValueObject() {
    }

    public InviteesValueObject(List<TeamMember> invitees) {
        this.invitees = invitees;
    }

    public List<TeamMember> getInvitees() {
        return invitees;
    }

    public void setInvitees(List<TeamMember> invitees) {
        this.invitees = invitees;
    }
}
