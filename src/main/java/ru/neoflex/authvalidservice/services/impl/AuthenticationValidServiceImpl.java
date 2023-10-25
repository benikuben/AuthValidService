package ru.neoflex.authvalidservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.neoflex.authvalidservice.dtos.AuthenticationRequest;
import ru.neoflex.authvalidservice.dtos.AuthenticationResponse;
import ru.neoflex.authvalidservice.dtos.RegisterRequest;
import ru.neoflex.authvalidservice.exceptions.AuthException;
import ru.neoflex.authvalidservice.exceptions.NotFoundException;
import ru.neoflex.authvalidservice.models.Role;
import ru.neoflex.authvalidservice.models.User;
import ru.neoflex.authvalidservice.security.UserDetailsImpl;
import ru.neoflex.authvalidservice.services.AuthenticationValidService;
import ru.neoflex.authvalidservice.services.UserService;
import ru.neoflex.authvalidservice.services.ValidRefererService;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationValidServiceImpl implements AuthenticationValidService {
    private final ValidRefererService validRefererService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        log.info("RegisterRequest {}", request);
        Optional<User> userFromDb = userService.findByUsername(request.getUsername());
        if (userFromDb.isPresent())
            throw new AuthException("User already exists");

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userService.save(user);
        String jwtToken = jwtService.generateToken(new UserDetailsImpl(user));
        log.info("jwt token {}", jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info("AuthenticationRequest {}", request);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userService.findByUsername(request.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found"));
        String jwtToken = jwtService.generateToken(new UserDetailsImpl(user));
        log.info("jwt token {}", jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public void validateToken(String authorization, String referer) {
        log.info("authorization header {}, referer header {}", authorization, referer);
        validRefererService.findByUrl(referer)
                .orElseThrow(() -> new NotFoundException(String.format("%s is not registered as in the AuthValidService system", referer)));
    }
}
