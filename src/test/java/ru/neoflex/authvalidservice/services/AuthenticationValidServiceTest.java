package ru.neoflex.authvalidservice.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.neoflex.authvalidservice.exceptions.NotFoundException;
import ru.neoflex.authvalidservice.services.impl.AuthenticationValidServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationValidServiceTest {
    @Mock
    private ValidRefererService validRefererService;

    @InjectMocks
    private AuthenticationValidServiceImpl authenticationValidService;

    @Test
    void validateToken() {
        when(validRefererService.findByUrl(any()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> authenticationValidService.validateToken("authorization", "referer"));
        verify(validRefererService, times(1)).findByUrl(any());
    }
}