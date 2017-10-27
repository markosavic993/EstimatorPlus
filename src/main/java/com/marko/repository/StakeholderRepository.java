package com.marko.repository;

import com.marko.model.Stakeholder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by msav on 10/27/2017.
 */
public interface StakeholderRepository extends JpaRepository<Stakeholder, Integer>{
    Stakeholder findStakeholderByName(String s);
}
