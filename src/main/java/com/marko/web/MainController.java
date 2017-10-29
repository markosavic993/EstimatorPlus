package com.marko.web;

import com.marko.model.*;
import com.marko.repository.FeatureRepository;
import com.marko.repository.StakeholderRepository;
import com.marko.repository.TechnologyRepository;
import com.marko.service.EstimationType;
import com.marko.service.EstimatorConfiguration;
import com.marko.service.TeamService;
import com.marko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        model.addAttribute("projectToEstimate", new Project());

        return "estimations";
    }

    @PostMapping("/save-project")
    public String saveProject(Model model, @ModelAttribute Project projectToEstimate, @ModelAttribute EstimatorConfiguration config) {
        System.out.println("project object fetched from frontend!");
        projectToEstimate.toString();
        return "redirect:/organize-estimation";
    }

}
