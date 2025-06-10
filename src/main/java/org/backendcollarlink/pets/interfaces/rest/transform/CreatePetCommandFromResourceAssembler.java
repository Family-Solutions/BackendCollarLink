package org.backendcollarlink.pets.interfaces.rest.transform;

import org.backendcollarlink.pets.domain.model.commands.CreatePetCommand;
import org.backendcollarlink.pets.interfaces.rest.resources.CreatePetResource;

public class CreatePetCommandFromResourceAssembler {
    public static CreatePetCommand toCommandFromResource(CreatePetResource resource) {
        return new CreatePetCommand(resource.username(), resource.collarId(), resource.name(), resource.species(), resource.breed(), resource.gender(), resource.age());
    }
}
