package ru.neoflex.authvalidservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.authvalidservice.models.ValidReferer;

import java.util.Optional;

@Repository
public interface ValidRefererRepository extends JpaRepository<ValidReferer, Long> {
    Optional<ValidReferer> findByUrl(String url);
}
