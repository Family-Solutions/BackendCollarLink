package org.backendcollarlink.pets.interfaces.rest.transform;

import org.backendcollarlink.pets.domain.model.commands.UpdatePetCollarCommand;
import org.backendcollarlink.pets.interfaces.rest.resources.UpdatePetCollarResource;

public class UpdatePetCollarCommandFromResourceAssembler {
    public static UpdatePetCollarCommand toCommandFromResource(Long petId, UpdatePetCollarResource resource) {
        return new UpdatePetCollarCommand(petId, resource.collarId());
    }
}
