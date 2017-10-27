package com.marko.repository;

import com.marko.model.ScrumTools;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by msav on 10/27/2017.
 */
public interface ScrumToolsRepository extends JpaRepository<ScrumTools, Integer> {
    ScrumTools findByName(String name);
}
