package org.backendcollarlink.pets.infrastructure.persistence.jpa.repositories;

import org.backendcollarlink.pets.domain.model.aggregates.Collar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollarRepository extends JpaRepository<Collar, Long> {
    List<Collar> findAllByUserUsername(String username);
}
