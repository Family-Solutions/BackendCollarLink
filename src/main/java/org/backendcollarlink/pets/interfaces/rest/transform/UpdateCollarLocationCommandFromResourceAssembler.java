package org.backendcollarlink.pets.interfaces.rest.transform;

import org.backendcollarlink.pets.domain.model.commands.UpdateCollarLocationCommand;
import org.backendcollarlink.pets.interfaces.rest.resources.UpdateCollarLocationResource;
import org.backendcollarlink.pets.interfaces.rest.resources.UpdateCollarResource;

public class UpdateCollarLocationCommandFromResourceAssembler {
    public static UpdateCollarLocationCommand toCommandFromResource(UpdateCollarLocationResource resource) {
        return new UpdateCollarLocationCommand(resource.serialNumber(), resource.lastLatitude(), resource.lastLongitude());
    }
}
