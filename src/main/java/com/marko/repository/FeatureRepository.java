package com.marko.repository;

import com.marko.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by msav on 10/26/2017.
 */
public interface FeatureRepository extends JpaRepository<Feature, Integer> {
    Feature findFeatureByName(String name);
}
