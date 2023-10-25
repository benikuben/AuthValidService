package ru.neoflex.authvalidservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neoflex.authvalidservice.models.ValidReferer;
import ru.neoflex.authvalidservice.repositories.ValidRefererRepository;
import ru.neoflex.authvalidservice.services.ValidRefererService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ValidRefererServiceImpl implements ValidRefererService {
    private final ValidRefererRepository validRefererRepository;

    @Override
    public Optional<ValidReferer> findByUrl(String url) {
        return validRefererRepository.findByUrl(url);
    }
}
