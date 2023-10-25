package ru.neoflex.authvalidservice.services;

import ru.neoflex.authvalidservice.models.ValidReferer;

import java.util.Optional;

public interface ValidRefererService {
    Optional<ValidReferer> findByUrl(String url);
}
