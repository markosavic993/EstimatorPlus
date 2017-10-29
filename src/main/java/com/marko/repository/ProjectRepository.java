package com.marko.repository;

import com.marko.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by msav on 10/29/2017.
 */
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
