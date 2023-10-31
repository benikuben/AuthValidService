package ru.neoflex.authvalidservice.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import ru.neoflex.authvalidservice.services.impl.JwtServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class JwtServiceTest {
    private static final JwtServiceImpl jwtService = new JwtServiceImpl();

    @BeforeAll
    public static void setUp() {
        ReflectionTestUtils.setField(jwtService, "secretKey", "TestSecretKeyValueForJwtService123456789012345");
    }

    @Test
    void validateToken() {
        String token1 = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRlc3QiLCJzdWIiOiJ0ZXN0IiwiaWF0IjoxNjk4NzgwNjIxLCJleHAiOjE2OTg3ODQyMjF9.1b6dwdWtKaade6ICgaOfZtko7ZQINUqMuMO9iQ5MteA";
        boolean tokenExpired = jwtService.validateToken(token1);
        assertFalse(tokenExpired);

        String token2 = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRlc3QiLCJzdWIiOiJ0ZXN0IiwiaWF0IjoxNjk4NzgwNjIxLCJleHAiOjE2OTg3ODQyMjF9.1b6dwdWtKaade6ICgaOfZtko7ZQINjhMuMO9iQ5MteD";
        boolean invalidSignature = jwtService.validateToken(token2);
        assertFalse(invalidSignature);

        String token3 = "eyJhbGciOiJIUzI1tko7ZQINjhMuMO9iQ5MteD";
        boolean malformedJwt = jwtService.validateToken(token3);
        assertFalse(malformedJwt);

        String token4 = "";
        boolean invalidToken = jwtService.validateToken(token4);
        assertFalse(invalidToken);
    }
}