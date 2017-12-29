package com.marko.web;

import com.google.common.collect.Lists;
import com.marko.model.*;
import com.marko.model.context.EstimationContext;
import com.marko.repository.*;
import com.marko.service.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by msav on 8/1/2017.
 */
@Controller
@RequestMapping("/estimator")
public class AppController {

    @Autowired
    private UserService userService;
    //    @Autowired
//    private TeamService teamService;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private StakeholderRepository stakeholderRepository;
    @Autowired
    private TechnologyRepository technologyRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EstimatorService estimatorService;

    @RequestMapping("/organize-estimation")
    public String navigateToEstimations(Model model) {
        String username = "Simon";
        Optional<User> user = userService.findUser(username);

        model.addAttribute("estimationTypes", EstimationType.values());
        model.addAttribute("types", ProjectType.values());
        model.addAttribute("uiImpacts", UserInterfaceImpact.values());
        model.addAttribute("refactoringLevels", RefactoringLevel.values());
        model.addAttribute("protocols", CommunicationProtocol.values());
        model.addAttribute("technologies", technologyRepository.findAll());
        model.addAttribute("features", featureRepository.findAll());
        model.addAttribute("stakeholders", stakeholderRepository.findAll());
        model.addAttribute("team", user.get().getTeam());
        model.addAttribute("teamMembers", user.get().getTeam().getMembers());
        model.addAttribute("config", new EstimatorConfiguration());
        model.addAttribute("inviteesObject", new InviteesValueObject());
        model.addAttribute("projectToEstimate", new Project());

        return "estimations";
    }

    @PostMapping("/organize-estimation")
    public String saveProject(Model model,
                              @ModelAttribute Project projectToEstimate,
                              @ModelAttribute EstimatorConfiguration config,
                              @ModelAttribute InviteesValueObject inviteesObject) {

        List<TeamMember> invitees = inviteesObject
                .getInvitees()
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        Team team = fetchTeam(invitees);

        //teamService.inviteAttendees(invitees);
        String estimate = estimatorService.estimate(projectToEstimate, team, config);
        Project savedProject = projectRepository.save(projectToEstimate);
        List<EstimationAtendee> attendees = invitees.stream()
                .map(teamMember -> new EstimationAtendee(teamMember, getRandomImgPath(), "?"))
                .collect(Collectors.toList());

        RefinementValueObject refinementObject = new RefinementValueObject(attendees, estimate, savedProject, "", EstimationContext.REFINEMENT);
        DataCache.addToCache(refinementObject);
        model.addAttribute("refinementObject", refinementObject);

        return "refinement";
    }

    private Team fetchTeam(List<TeamMember> invitees) {
        return teamRepository.findAll()
                .stream()
                .filter(team -> team.getMembers().contains(invitees.get(0)))
                .findFirst()
                .get();
    }

    @GetMapping("/reveal")
    public String reveal(Model model, @RequestParam("objectId") int id) throws IOException {

        RefinementValueObject refinementObject = DataCache.getFromCache(id);

        refinementObject.getAttendees().forEach(estimationAttendee -> estimationAttendee.setEstimation(createRandomEstimates(refinementObject.getEstimation())));
        refinementObject.setContext(EstimationContext.REVEALMENT);
        refinementObject.setExplanationText(FileUtils.readFileToString(new java.io.File("report_" + refinementObject.getProject().getProjectName())));

        model.addAttribute("refinementObject", refinementObject);

        return "refinement";
    }

    @GetMapping("/refinement")
    public String newRound(Model model, @RequestParam("objectId") int id) {

        RefinementValueObject refinementObject = DataCache.getFromCache(id);

        refinementObject.getAttendees().forEach(estimationAttendee -> estimationAttendee.setEstimation("?"));
        refinementObject.setContext(EstimationContext.REFINEMENT);
        refinementObject.setExplanationText("");

        model.addAttribute("refinementObject", refinementObject);

        return "refinement";
    }

    private String createRandomEstimates(String estimation) {
        Random rand = new Random();
        ArrayList<String> storyPoints = Lists.newArrayList("1", "3", "5", "8", "13", "21", "34", "55");
        ArrayList<String> tshirtSizes = Lists.newArrayList("XS", "S", "M", "L", "XL", "XXL");

        try {
            Integer.parseInt(estimation);
            return storyPoints.get(rand.nextInt(storyPoints.size()));
        } catch (Exception ex) {
            return tshirtSizes.get(rand.nextInt(tshirtSizes.size()));
        }
    }

    private String getRandomImgPath() {
        ArrayList<String> paths = Lists.newArrayList("avatar_1.jpg", "avatar_3.jpg", "avatar_4.jpg", "avatar_5.png", "avatar_6.png", "avatar_7.jpg");
        Random rand = new Random();
        return paths.get(rand.nextInt(paths.size()));
    }

}
