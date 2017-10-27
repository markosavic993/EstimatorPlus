package com.marko.repository;

import com.marko.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by msav on 10/24/2017.
 */
public interface TeamRepository extends JpaRepository<Team, Integer> {
    Team findTeamByTeamName(String teamName);
}
