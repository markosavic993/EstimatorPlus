package com.marko.service.mapper;

import com.marko.model.Project;
import model.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by msav on 11/1/2017.
 */
public class ProjectMapper {
    public static model.Project toEstimatorProject(Project projectFromWebApp) {
        return new ProjectBuilder()
                .setProjectName(projectFromWebApp.getProjectName())
                .setCommunicationProtocol(CommunicationProtocol.valueOf(projectFromWebApp.getCommunicationProtocol().name()))
                .setDomain(projectFromWebApp.getDomain())
                .setFeatures(mapFeaturesList(projectFromWebApp))
                .setIntegrationTestNeeded(projectFromWebApp.isIntegrationTestNeeded())
                .setJavascriptTestNeeded(projectFromWebApp.isJavascriptTestNeeded())
                .setUnitTestsNeeded(projectFromWebApp.isUnitTestsNeeded())
                .setWebtestNeeded(projectFromWebApp.isWebtestNeeded())
                .setRefactoringLevel(RefactoringLevel.valueOf(projectFromWebApp.getRefactoringLevel().name()))
                .setRequestedTechnologies(mapTechnologiesList(projectFromWebApp))
                .setStakeholders(mapStakeholdersList(projectFromWebApp))
                .setUiImpact(UserInterfaceImpact.valueOf(projectFromWebApp.getUiImpact().name()))
                .setComplexity(new Complexity(0, 0, 0))
                .createProject();
    }

    private static List<Stakeholder> mapStakeholdersList(Project projectFromWebApp) {
        return projectFromWebApp.getStakeholders()
                .stream()
                .map(stakeholder -> new Stakeholder(stakeholder.getName(), StakeholderType.valueOf(stakeholder.getType().name())))
                .collect(Collectors.toList());
    }

    private static List<Technology> mapTechnologiesList(Project projectFromWebApp) {
        return projectFromWebApp.getRequestedTechnologies()
                .stream()
                .map(technology -> new Technology(technology.getName(), Popularity.valueOf(technology.getPopularity().name())))
                .collect(Collectors.toList());
    }

    private static List<Feature> mapFeaturesList(Project projectFromWebApp) {
        return projectFromWebApp.getFeatures()
                .stream()
                .map(feature -> new Feature(feature.getName(), feature.getDescription()))
                .collect(Collectors.toList());
    }
}
