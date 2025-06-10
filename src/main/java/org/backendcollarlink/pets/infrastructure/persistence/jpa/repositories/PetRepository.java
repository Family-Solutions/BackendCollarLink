package org.backendcollarlink.pets.infrastructure.persistence.jpa.repositories;

import org.backendcollarlink.pets.domain.model.aggregates.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByUserUsername(String username);
    Optional<Pet> findByCollarId(Long collarId);
}
