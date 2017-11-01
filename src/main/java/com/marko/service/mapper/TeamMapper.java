package com.marko.service.mapper;

import com.marko.model.SeniorityLevel;
import com.marko.model.Team;
import model.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by msav on 11/1/2017.
 */
public class TeamMapper {
    public static model.Team toEstimatorTeam(Team teamFromWebApp) {
        return new TeamBuilder()
                .setDistributed(teamFromWebApp.isDistributed())
                .setFamiliarDomains(teamFromWebApp.getFamiliarDomains())
                .setKnownFeatures(mapFeaturesList(teamFromWebApp))
                .setNumberOfMembers(teamFromWebApp.getMembers().size())
                .setTechnologies(mapTechnologiesList(teamFromWebApp))
                .setScrumTools(mapScrumToolsList(teamFromWebApp))
                .setTeamName(teamFromWebApp.getTeamName())
                .setStructure(createTeamStructure(teamFromWebApp))
                .setStakeholderExperienceMap(mapStakeholdersList(teamFromWebApp))
                .setProjects(new ArrayList<>())
                .createTeam();
    }

    private static Map<Stakeholder, StakeholderExperience> mapStakeholdersList(Team teamFromWebApp) {
        Map<Stakeholder, StakeholderExperience> stakeholderMap = new HashMap<>();
        teamFromWebApp.getKnownStakeholders()
                .stream()
                .map(stakeholder -> new Stakeholder(stakeholder.getName(), StakeholderType.valueOf(stakeholder.getType().name())))
                .forEach(stakeholder -> stakeholderMap.put(stakeholder, getRandomExperience()));

        return stakeholderMap;
    }

    private static StakeholderExperience getRandomExperience() {
        StakeholderExperience[] values = StakeholderExperience.values();
        Random rand = new Random();

        return values[rand.nextInt(values.length)];
    }

    private static TeamStructure createTeamStructure(Team teamFromWebApp) {
        int numOfJuniors = (int) teamFromWebApp.getMembers()
                .stream()
                .filter(teamMember -> teamMember.getSeniorityLevel().equals(SeniorityLevel.JUNIOR) || teamMember.getSeniorityLevel().equals(SeniorityLevel.MEDIOR))
                .count();
        int numOfSeniors = (int) teamFromWebApp.getMembers()
                .stream()
                .filter(teamMember -> teamMember.getSeniorityLevel().equals(SeniorityLevel.SENIOR) || teamMember.getSeniorityLevel().equals(SeniorityLevel.LEAD))
                .count();

        return new TeamStructure(numOfJuniors, numOfSeniors);
    }

    private static List<ScrumTools> mapScrumToolsList(Team teamFromWebApp) {
        return teamFromWebApp.getScrumTools()
                .stream()
                .map(scrumTools -> new ScrumTools(scrumTools.getName(), scrumTools.getDescription()))
                .collect(Collectors.toList());
    }

    private static List<Technology> mapTechnologiesList(Team teamFromWebApp) {
        return teamFromWebApp.getTechnologies()
                .stream()
                .map(technology -> new Technology(technology.getName(), Popularity.valueOf(technology.getPopularity().name())))
                .collect(Collectors.toList());
    }

    private static List<Feature> mapFeaturesList(Team teamFromWebApp) {
        return teamFromWebApp.getKnownFeatures()
                .stream()
                .map(feature -> new Feature(feature.getName(), feature.getDescription()))
                .collect(Collectors.toList());
    }
}
