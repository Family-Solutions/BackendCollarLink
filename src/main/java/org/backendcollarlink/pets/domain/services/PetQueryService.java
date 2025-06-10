package org.backendcollarlink.pets.domain.services;

import org.backendcollarlink.pets.domain.model.aggregates.Pet;
import org.backendcollarlink.pets.domain.model.queries.GetAllPetsByUsernameQuery;
import org.backendcollarlink.pets.domain.model.queries.GetPetByCollarIdQuery;
import org.backendcollarlink.pets.domain.model.queries.GetPetByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PetQueryService {
    List<Pet> handle(GetAllPetsByUsernameQuery query);
    Optional<Pet> handle(GetPetByIdQuery query);
    Optional<Pet> handle(GetPetByCollarIdQuery query);
}
