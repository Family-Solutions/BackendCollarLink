package org.backendcollarlink.pets.domain.services;

import org.backendcollarlink.pets.domain.model.aggregates.Pet;
import org.backendcollarlink.pets.domain.model.commands.CreatePetCommand;
import org.backendcollarlink.pets.domain.model.commands.DeletePetCommand;
import org.backendcollarlink.pets.domain.model.commands.UpdatePetCollarCommand;
import org.backendcollarlink.pets.domain.model.commands.UpdatePetCommand;

import java.util.Optional;

public interface PetCommandService {
    Optional<Pet> handle(CreatePetCommand command);
    Optional<Pet> handle(UpdatePetCommand command);
    Optional<Pet> handle(UpdatePetCollarCommand command);
    Optional<Pet> handle(DeletePetCommand command);
}
