package com.marko.service;

import com.marko.model.TeamMember;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by msav on 10/28/2017.
 */
@Service
public interface TeamService {
    void inviteAttendees(List<TeamMember> invitees);
}
