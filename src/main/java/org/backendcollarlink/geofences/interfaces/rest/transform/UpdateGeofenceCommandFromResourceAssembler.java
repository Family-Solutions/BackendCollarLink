package org.backendcollarlink.geofences.interfaces.rest.transform;

import org.backendcollarlink.geofences.domain.model.commands.UpdateGeofenceCommand;
import org.backendcollarlink.geofences.interfaces.rest.resources.UpdateGeofenceResource;

public class UpdateGeofenceCommandFromResourceAssembler {
    public static UpdateGeofenceCommand toCommandFromResource(Long geofenceId,UpdateGeofenceResource resource) {
        return new UpdateGeofenceCommand(geofenceId, resource.latitude(), resource.longitude(), resource.radius());
    }
}
