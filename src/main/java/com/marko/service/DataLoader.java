package com.marko.service;

import com.google.common.collect.Lists;
import com.marko.model.*;
import com.marko.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msav on 10/26/2017.
 */
@Service
public class DataLoader {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private TechnologyRepository technologyRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;
    @Autowired
    private StakeholderRepository stakeholderRepository;
    @Autowired
    private ScrumToolsRepository scrumToolsRepository;

    public void initData() {
        createFeatureList();
        createTechnologyList();
        createStakeholdersList();
        createScrumToolsList();
        createTeamSkipper();
        createUsers();
    }

    private void createUsers() {
        ArrayList<User> users = Lists.newArrayList(
                new User("Marko", "123", teamRepository.findTeamByTeamName("Skipper"), "marko@skipper.com"),
                new User("Teja", "123", teamRepository.findTeamByTeamName("Skipper"), "teja@skipper.com"),
                new User("Simon", "123", teamRepository.findTeamByTeamName("Skipper"), "simon@skipper.com")
        );

        userRepository.save(users);
    }

    private void createScrumToolsList() {
        ArrayList<ScrumTools> scrumTools = Lists.newArrayList(
                new ScrumTools("Jira", "Attlasian bug tracking tool"),
                new ScrumTools("Slack", "Chating tool"),
                new ScrumTools("HipChat", "Chating tool"),
                new ScrumTools("Estimator Plus", "Estimation Expert System"),
                new ScrumTools("Scrum it easy", "Agile planning tool"),
                new ScrumTools("Tool Astro", "Agile planning tool for non kanban teams"),
                new ScrumTools("Trello", "Bug tracking tool"),
                new ScrumTools("Burn it efficiently", "Burn down generator gadget")
        );

        scrumToolsRepository.save(scrumTools);
    }

    private void createStakeholdersList() {
        ArrayList<Stakeholder> stakeholders = Lists.newArrayList(
                new Stakeholder("Andrea Tschanz", StakeholderType.BUSSINES),
                new Stakeholder("Romeo Stenn", StakeholderType.BUSSINES),
                new Stakeholder("Lea Brone", StakeholderType.BUSSINES),
                new Stakeholder("Nicole Bruner", StakeholderType.MANAGER),
                new Stakeholder("Tamara Vidmer", StakeholderType.MANAGER),
                new Stakeholder("Patrick Senn", StakeholderType.MANAGER),
                new Stakeholder("Tibco", StakeholderType.EXTERNAL_SYSTEM),
                new Stakeholder("Blueadit", StakeholderType.EXTERNAL_SYSTEM),
                new Stakeholder("Symphony", StakeholderType.EXTERNAL_SYSTEM),
                new Stakeholder("Siebel", StakeholderType.EXTERNAL_SYSTEM),
                new Stakeholder("C4", StakeholderType.EXTERNAL_SYSTEM),
                new Stakeholder("Combox", StakeholderType.EXTERNAL_SYSTEM),
                new Stakeholder("Felix Huber", StakeholderType.BUSSINES_ANALYST),
                new Stakeholder("Isabel Scharer", StakeholderType.BUSSINES_ANALYST),
                new Stakeholder("Christoph Heuert", StakeholderType.BUSSINES_ANALYST),
                new Stakeholder("Reto Eicholzer", StakeholderType.BUSSINES_ANALYST),
                new Stakeholder("Georg Seeli", StakeholderType.BUSSINES_ANALYST),
                new Stakeholder("Oliver Muller", StakeholderType.BUSSINES_ANALYST),
                new Stakeholder("Alexander Kohler", StakeholderType.DEVELOPER),
                new Stakeholder("Daniel Suter", StakeholderType.DEVELOPER),
                new Stakeholder("David Miric", StakeholderType.DEVELOPER),
                new Stakeholder("Bruno Ritz", StakeholderType.DEVELOPER),
                new Stakeholder("Igor Jovic", StakeholderType.DEVELOPER),
                new Stakeholder("Ana Milutinovic", StakeholderType.QA),
                new Stakeholder("Danilo Vasojevic", StakeholderType.QA),
                new Stakeholder("Paschali Bimpisidis", StakeholderType.QA)
        );

        stakeholderRepository.save(stakeholders);
    }

    private void createTechnologyList() {
        ArrayList<Technology> technologies = Lists.newArrayList(
                new Technology("Java", Popularity.HIGH),
                new Technology("Java 8", Popularity.MEDIUM),
                new Technology("Javascript", Popularity.HIGH),
                new Technology(".NET", Popularity.HIGH),
                new Technology("HTML", Popularity.HIGH),
                new Technology("CSS", Popularity.HIGH),
                new Technology("Spring", Popularity.HIGH),
                new Technology("Spring Boot", Popularity.MEDIUM),
                new Technology("Clojure", Popularity.EXPERIMENTAL),
                new Technology("F#", Popularity.LOW),
                new Technology("Kotlin", Popularity.EXPERIMENTAL),
                new Technology("Android", Popularity.HIGH),
                new Technology("Swift", Popularity.MEDIUM),
                new Technology("Maven", Popularity.HIGH),
                new Technology("Gradle", Popularity.MEDIUM)
        );

        technologyRepository.save(technologies);
    }

    private void createTeamSkipper() {
        Team skipper = new Team();
        skipper.setTeamName("Skipper");
        skipper.setDistributed(true);
        skipper.setFamiliarDomains(
                Lists.newArrayList("Employee management", "Partner center", "Combox", "Bonding", "TWIX", "AGB"));
        skipper.setKnownFeatures(getSkipperFeatures());
        skipper.setTechnologies(getSkipperTechnologies());
        skipper.setMembers(createSkipperTeamMembers());
        skipper.setKnownStakeholders(getSkipperStakeholders());
        skipper.setScrumTools(getSkipperScrumTools());
        skipper.setProjects(Lists.newArrayList());

        teamRepository.save(skipper);
    }

    private List<ScrumTools> getSkipperScrumTools() {
        return Lists.newArrayList(
                scrumToolsRepository.findByName("Jira"),
                scrumToolsRepository.findByName("Trello"),
                scrumToolsRepository.findByName("Slack"),
                scrumToolsRepository.findByName("HipChat"),
                scrumToolsRepository.findByName("Estimator Plus")
        );
    }

    private List<Stakeholder> getSkipperStakeholders() {
        return Lists.newArrayList(
                stakeholderRepository.findStakeholderByName("Andrea Tschanz"),
                stakeholderRepository.findStakeholderByName("Nicole Bruner"),
                stakeholderRepository.findStakeholderByName("Tamara Vidmer"),
                stakeholderRepository.findStakeholderByName("Tibco"),
                stakeholderRepository.findStakeholderByName("Blueadit"),
                stakeholderRepository.findStakeholderByName("Combox"),
                stakeholderRepository.findStakeholderByName("David Miric"),
                stakeholderRepository.findStakeholderByName("Bruno Ritz"),
                stakeholderRepository.findStakeholderByName("Ana Milutinovic"),
                stakeholderRepository.findStakeholderByName("Felix Huber"),
                stakeholderRepository.findStakeholderByName("Christoph Heuert"),
                stakeholderRepository.findStakeholderByName("Isabel Scharer")
        );
    }

    private List<TeamMember> createSkipperTeamMembers() {
        ArrayList<TeamMember> teamMembers = Lists.newArrayList(
                new TeamMember("nenad@skipper.com", "Nenad Vitorovic", SeniorityLevel.SENIOR),
                new TeamMember("janko@skipper.com", "Janko Sokolovic", SeniorityLevel.MEDIOR),
                new TeamMember("marko@skipper.com", "Marko Savic", SeniorityLevel.JUNIOR),
                new TeamMember("franziska@skipper.com", "Franziska Meyer", SeniorityLevel.LEAD),
                new TeamMember("oliver@skipper.com", "Oliver Zihler", SeniorityLevel.JUNIOR),
                new TeamMember("mihailo@skipper.com", "Mihailo Matijevic", SeniorityLevel.JUNIOR),
                new TeamMember("kristina@skipper.com", "Kristina Mijajlovic", SeniorityLevel.MEDIOR),
                new TeamMember("andrea@skipper.com", "Andrea Mathis", SeniorityLevel.SENIOR)
        );

        teamMemberRepository.save(teamMembers);
        return teamMembers;
    }

    private List<Technology> getSkipperTechnologies() {
        return Lists.newArrayList(
                technologyRepository.findTechnologyByName("Java"),
                technologyRepository.findTechnologyByName("Java 8"),
                technologyRepository.findTechnologyByName("Javascript"),
                technologyRepository.findTechnologyByName("Gradle"),
                technologyRepository.findTechnologyByName("Maven"),
                technologyRepository.findTechnologyByName("Clojure"),
                technologyRepository.findTechnologyByName("HTML")
        );
    }

    private List<Feature> getSkipperFeatures() {
        return Lists.newArrayList(
                featureRepository.findFeatureByName("Rest interface"),
                featureRepository.findFeatureByName("Soap interface"),
                featureRepository.findFeatureByName("Feature toggles"),
                featureRepository.findFeatureByName("CDC Testing"),
                featureRepository.findFeatureByName("Frontend engineering")
        );
    }

    private void createFeatureList() {
        ArrayList<Feature> features = Lists.newArrayList(
                new Feature("Rest interface", "Implementation of rest api that communicates with external system!"),
                new Feature("Soap interface", "Implementation of soap api that communicates with external system!"),
                new Feature("Feature toggles", "Implementation of easily replaceble code fragments!"),
                new Feature("CDC Testing", "Createing CDC tests for testing interoperability of systems!"),
                new Feature("Designing API", "Implementing user friendly and consumer driven interfaces!"),
                new Feature("Designing Database", "Implementing of well structured database!"),
                new Feature("Frontend engineering", "Implementing of cleanly structured frontend functionality!")
        );

        featureRepository.save(features);
    }

}
