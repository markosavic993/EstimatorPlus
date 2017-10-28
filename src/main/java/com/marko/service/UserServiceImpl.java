package com.marko.service;

import com.marko.repository.TeamRepository;
import com.marko.repository.UserRepository;
import com.marko.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by msav on 8/1/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<User> loadAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUser(User user) {
        User foundUser = userRepository.getOne(user.getUsername());
        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            return Optional.of(foundUser);
        }
        return Optional.empty();
    }

    @Override
    public List<User> loadAllFromTeam(User user) {
        return userRepository.findAll()
                .stream()
                .filter(user1 -> user.getTeam().equals(user1.getTeam()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findUser(String username) {
        return Optional.of(userRepository.getOne(username));
    }
}
