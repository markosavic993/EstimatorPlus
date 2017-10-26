package com.marko.service;

import com.marko.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by msav on 8/1/2017.
 */
public interface UserService {
    List<User> loadAll();

    Optional<User> findUser(User user);

    List<User> loadAllFromTeam(User user);
}
