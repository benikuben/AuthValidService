package ru.neoflex.authvalidservice.services;

import ru.neoflex.authvalidservice.models.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String login);

    void save(User user);
}
