package org.backendcollarlink.geofences.interfaces.rest.transform;

import org.backendcollarlink.geofences.domain.model.commands.CreateGeofenceCommand;
import org.backendcollarlink.geofences.interfaces.rest.resources.CreateGeofenceResource;

public class CreateGeofenceCommandFromResourceAssembler {
    public static CreateGeofenceCommand toCommandFromResource(CreateGeofenceResource resource) {
        return new CreateGeofenceCommand(resource.name(), resource.latitude(), resource.longitude(), resource.radius(), resource.username());
    }
}
