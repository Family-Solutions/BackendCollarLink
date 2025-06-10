package org.backendcollarlink.pets.applications.internal.queryservices;

import org.backendcollarlink.pets.domain.model.aggregates.Pet;
import org.backendcollarlink.pets.domain.model.queries.GetAllPetsByUsernameQuery;
import org.backendcollarlink.pets.domain.model.queries.GetPetByCollarIdQuery;
import org.backendcollarlink.pets.domain.model.queries.GetPetByIdQuery;
import org.backendcollarlink.pets.domain.services.PetQueryService;
import org.backendcollarlink.pets.infrastructure.persistence.jpa.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetQueryServiceImpl implements PetQueryService {
    private final PetRepository petRepository;

    public PetQueryServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> handle(GetAllPetsByUsernameQuery query) {
        return petRepository.findAllByUserUsername(query.username());
    }

    @Override
    public Optional<Pet> handle(GetPetByIdQuery query) {
        return petRepository.findById(query.id());
    }

    @Override
    public Optional<Pet> handle(GetPetByCollarIdQuery query) {
        return petRepository.findByCollarId(query.collarId());
    }
}
