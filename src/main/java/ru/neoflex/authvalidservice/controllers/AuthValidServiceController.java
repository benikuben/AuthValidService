package ru.neoflex.authvalidservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.authvalidservice.dtos.AuthenticationRequest;
import ru.neoflex.authvalidservice.dtos.AuthenticationResponse;
import ru.neoflex.authvalidservice.dtos.RegisterRequest;
import ru.neoflex.authvalidservice.services.AuthenticationValidService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthValidServiceController implements AuthValidServiceAPI {
    private final AuthenticationValidService authenticationValidService;

    @Override
    public ResponseEntity<AuthenticationResponse> register(RegisterRequest request) {
        return new ResponseEntity<>(authenticationValidService.register(request), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        return new ResponseEntity<>(authenticationValidService.authenticate(request), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> validateToken(String authorization, String referer) {
        authenticationValidService.validateToken(authorization, referer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
