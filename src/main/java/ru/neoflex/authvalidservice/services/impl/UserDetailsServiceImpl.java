package ru.neoflex.authvalidservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.neoflex.authvalidservice.exceptions.NotFoundException;
import ru.neoflex.authvalidservice.repositories.UserRepository;
import ru.neoflex.authvalidservice.security.UserDetailsImpl;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailsImpl(
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new NotFoundException("User not found"))
        );
    }
}
