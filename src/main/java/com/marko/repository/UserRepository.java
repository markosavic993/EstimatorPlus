package com.marko.repository;

import com.marko.model.Team;
import com.marko.model.TeamMember;
import com.marko.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by msav on 8/1/2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByTeam(Team team);

    List<TeamMember> findUserByTeam(Team team);
}
