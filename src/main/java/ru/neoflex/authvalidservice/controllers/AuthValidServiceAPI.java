package ru.neoflex.authvalidservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.neoflex.authvalidservice.dtos.AuthenticationRequest;
import ru.neoflex.authvalidservice.dtos.AuthenticationResponse;
import ru.neoflex.authvalidservice.dtos.RegisterRequest;

public interface AuthValidServiceAPI {
    @PostMapping("/register")
    @Operation(
            summary = "Register",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Registered successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthenticationResponse.class)
                            )
                    )
            }
    )
    ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request);

    @PostMapping("/authenticate")
    @Operation(
            summary = "Authenticate",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Authenticated successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthenticationResponse.class)
                            )
                    )
            }
    )
    ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);

    @PostMapping("/token/validate")
    @Operation(
            summary = "Validate token",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Validated successfully"
                    )
            }
    )
    ResponseEntity<Void> validateToken(@RequestHeader(value = "Authorization", required = false) String authorization,
                                       @RequestHeader("referer") String referer);
}
