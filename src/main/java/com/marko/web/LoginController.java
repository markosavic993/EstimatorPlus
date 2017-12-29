package com.marko.web;

import com.marko.model.User;
import com.marko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

/**
 * Created by msav on 12/29/2017.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("user", new User());
        return "login";
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
}
