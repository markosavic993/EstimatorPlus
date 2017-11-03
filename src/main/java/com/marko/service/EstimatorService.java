package com.marko.service;

import com.marko.model.Project;
import com.marko.model.Team;
import com.marko.service.mapper.ProjectMapper;
import com.marko.service.mapper.TeamMapper;
import estimator.Estimator;
import estimator.EstimatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import rules.ProjectRulesType;

/**
 * Created by msav on 10/29/2017.
 */
@Service
public class EstimatorService {
    public int estimate(Project project, Team team, EstimatorConfiguration config) {
        Estimator estimator = new EstimatorBuilder()
                .createEstimator()
                .withExplanation()
                .forProjectType(fetchTypeFrom(project))
                .build();

        return estimator.estimateProjectForTeam(ProjectMapper.toEstimatorProject(project), TeamMapper.toEstimatorTeam(team)).getNumOfStoryPoints();
    }

    private ProjectRulesType fetchTypeFrom(Project project) {
        switch (project.getType()) {
            case BACKEND_PROJECT:
                return ProjectRulesType.BACKEND;
            case FRONTEND_PROJECT:
                return ProjectRulesType.FRONTEND;
            case FULL_STACK_PROJECT:
                return ProjectRulesType.DEFAULT;
            case REFACTORING_PROJECT:
                return ProjectRulesType.DEFAULT;
            default:
                throw new IllegalArgumentException();
        }
    }
}
