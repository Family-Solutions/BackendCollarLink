package org.backendcollarlink.pets.interfaces.rest.transform;

import org.backendcollarlink.pets.domain.model.commands.UpdateCollarCommand;
import org.backendcollarlink.pets.interfaces.rest.resources.UpdateCollarResource;

public class UpdateCollarCommandFromResourceAssembler {
    public static UpdateCollarCommand toCommandFromResource(Long collarId, UpdateCollarResource resource) {
        return new UpdateCollarCommand(collarId, resource.username(), resource.serialNumber(), resource.model(), resource.lastLatitude(), resource.lastLongitude());
    }
}
