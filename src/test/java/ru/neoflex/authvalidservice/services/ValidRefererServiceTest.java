package ru.neoflex.authvalidservice.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.neoflex.authvalidservice.models.ValidReferer;
import ru.neoflex.authvalidservice.repositories.ValidRefererRepository;
import ru.neoflex.authvalidservice.services.impl.ValidRefererServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidRefererServiceTest {
    @Mock
    private ValidRefererRepository validRefererRepository;

    @InjectMocks
    private ValidRefererServiceImpl validRefererService;

    @Test
    void findByUrl() {
        String url = "url";
        Optional<ValidReferer> expectedValidReferer1 = Optional.of(
                ValidReferer.builder().url(url).build()
        );
        Optional<ValidReferer> expectedValidReferer2 = Optional.empty();

        when(validRefererRepository.findByUrl(any()))
                .thenReturn(expectedValidReferer1, expectedValidReferer2);

        Optional<ValidReferer> actualValidReferer1 = validRefererService.findByUrl(url);
        Optional<ValidReferer> actualValidReferer2 = validRefererService.findByUrl(url);

        assertEquals(expectedValidReferer1, actualValidReferer1);
        assertEquals(expectedValidReferer2, actualValidReferer2);
    }
}