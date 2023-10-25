package ru.neoflex.authvalidservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neoflex.authvalidservice.models.User;
import ru.neoflex.authvalidservice.repositories.UserRepository;
import ru.neoflex.authvalidservice.services.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String login) {
        return userRepository.findByUsername(login);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
