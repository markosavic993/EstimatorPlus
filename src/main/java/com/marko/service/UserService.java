package com.marko.service;

import com.google.common.collect.Lists;
import com.marko.model.TeamMember;
import com.marko.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by msav on 8/1/2017.
 */
@Service
public interface UserService {
    List<User> loadAll();

    Optional<User> findUser(User user);

    List<User> loadAllFromTeam(User user);

    Optional<User> findUser(String username);
}
