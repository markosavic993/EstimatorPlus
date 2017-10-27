package com.marko.repository;

import com.marko.model.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by msav on 10/27/2017.
 */
public interface TeamMemberRepository extends JpaRepository<TeamMember, String>{
}
