package ru.neoflex.authvalidservice.services;

import ru.neoflex.authvalidservice.dtos.AuthenticationRequest;
import ru.neoflex.authvalidservice.dtos.AuthenticationResponse;
import ru.neoflex.authvalidservice.dtos.RegisterRequest;

public interface AuthenticationValidService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void validateToken(String authorization, String referer);
}
