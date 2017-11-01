package com.marko.service;

import com.marko.model.Project;
import com.marko.model.Team;
import com.marko.service.mapper.ProjectMapper;
import com.marko.service.mapper.TeamMapper;
import estimator.Estimator;
import estimator.EstimatorBuilder;
import model.StoryPoints;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import rules.ProjectRulesType;

/**
 * Created by msav on 10/29/2017.
 */
@Service
public class EstimatorService {
    public String estimate(Project project, Team team, EstimatorConfiguration config) {
        Estimator estimator;

        if (config.isWithExplanations()) {
            estimator = createEstimatorWithExplanations(project);
        } else {
            estimator = createEstimatorWithoutExplanations(project);
        }

        return getStoryPointsResult(project, team, estimator, config);
    }

    private String getStoryPointsResult(Project project, Team team, Estimator estimator, EstimatorConfiguration config) {
        StoryPoints storyPoints = estimator.estimateProjectForTeam(
                ProjectMapper.toEstimatorProject(project),
                TeamMapper.toEstimatorTeam(team));

        if (config.getEstimationType().equals(EstimationType.STORY_POINTS)) {
            return String.valueOf(storyPoints.getNumOfStoryPoints());
        }

        if (config.getEstimationType().equals(EstimationType.TSHIRT_SIZES)) {
            return storyPoints.getTshirtSize();
        }

        throw new IllegalArgumentException();
    }

    private Estimator createEstimatorWithoutExplanations(Project project) {
        return new EstimatorBuilder()
                .createEstimator()
                .forProjectType(fetchTypeFrom(project))
                .build();
    }

    private Estimator createEstimatorWithExplanations(Project project) {
        return new EstimatorBuilder()
                .createEstimator()
                .withExplanation()
                .forProjectType(fetchTypeFrom(project))
                .build();
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
