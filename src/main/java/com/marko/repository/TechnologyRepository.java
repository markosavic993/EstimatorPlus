package com.marko.repository;

import com.marko.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by msav on 10/26/2017.
 */
public interface TechnologyRepository extends JpaRepository<Technology, Integer> {
    Technology findTechnologyByName(String name);
}
