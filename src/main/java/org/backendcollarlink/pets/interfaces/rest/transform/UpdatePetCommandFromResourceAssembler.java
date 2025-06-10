package org.backendcollarlink.pets.interfaces.rest.transform;

import org.backendcollarlink.pets.domain.model.commands.UpdatePetCommand;
import org.backendcollarlink.pets.interfaces.rest.resources.UpdatePetResource;

public class UpdatePetCommandFromResourceAssembler {
    public static UpdatePetCommand toCommandFromResource(Long petId, UpdatePetResource resource) {
        return new UpdatePetCommand(petId, resource.name(), resource.species(), resource.breed(), resource.gender(), resource.age());
    }
}
