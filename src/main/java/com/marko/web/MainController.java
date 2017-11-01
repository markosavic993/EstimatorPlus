package com.marko.web;

import com.google.common.collect.Lists;
import com.marko.model.*;
import com.marko.model.context.EstimationContext;
import com.marko.repository.FeatureRepository;
import com.marko.repository.ProjectRepository;
import com.marko.repository.StakeholderRepository;
import com.marko.repository.TechnologyRepository;
import com.marko.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by msav on 8/1/2017.
 */
@Controller
public class MainController {

    @Autowired
    private UserService userService;
    //    @Autowired
//    private TeamService teamService;
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

    @RequestMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("user", new User());
        return "login"; // return index.html Template
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public String handleLogin(@ModelAttribute("SpringWeb") User user, Model model) {
        //personList.forEach(user -> System.out.println(user.getEmail()));
        Optional<User> userFromDatabase = userService.findUser(user);
        model.addAttribute("user", userFromDatabase.get());
        List<User> personList = userService.loadAllFromTeam(userFromDatabase.get());
        model.addAttribute("personList", personList);
        return "index";
    }

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

        //teamService.inviteAttendees(invitees);
        int estimate = estimatorService.estimate(projectToEstimate, config);
        Project savedProject = projectRepository.save(projectToEstimate);
        List<EstimationAtendee> attendees = invitees.stream()
                .map(teamMember -> new EstimationAtendee(teamMember, getRandomImgPath(), "?"))
                .collect(Collectors.toList());

        RefinementValueObject refinementObject = new RefinementValueObject(attendees, estimate, savedProject, "", EstimationContext.REFINEMENT);
        DataCache.addToCache(refinementObject);
        model.addAttribute("refinementObject", refinementObject);

        return "refinement";
    }

    @GetMapping("/reveal")
    public String reveal(Model model, @RequestParam("objectId") int id) {

        RefinementValueObject refinementObject = DataCache.getFromCache(id);

        refinementObject.getAttendees().forEach(estimationAttendee -> estimationAttendee.setEstimation("5"));
        refinementObject.setContext(EstimationContext.REVEALMENT);
        refinementObject.setExplanationText("Lorem Ipsum");

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

    private String getRandomImgPath() {
        ArrayList<String> paths = Lists.newArrayList("avatar_1.jpg", "avatar_3.jpg", "avatar_4.jpg", "avatar_5.png", "avatar_6.png", "avatar_7.jpg", "avatar_8.png");
        Random rand = new Random();
        return paths.get(rand.nextInt(paths.size()));
    }

}
