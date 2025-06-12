package org.backendcollarlink.pets.interfaces.rest.transform;

import org.backendcollarlink.pets.domain.model.commands.CreateCollarCommand;
import org.backendcollarlink.pets.interfaces.rest.resources.CreateCollarResource;

public class CreateCollarCommandFromResourceAssembler {
    public static CreateCollarCommand toCommandFromResource(CreateCollarResource resource) {
        return new CreateCollarCommand(resource.username(), resource.serialNumber(), resource.model(), resource.lastLatitude(), resource.lastLongitude());
    }
}
