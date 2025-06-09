package org.backendcollarlink.geofences.interfaces.rest.transform;

import org.backendcollarlink.geofences.domain.model.aggregates.Geofence;
import org.backendcollarlink.geofences.interfaces.rest.resources.GeofenceResource;

public class GeofenceResourceFromEntityAssembler {
    public static GeofenceResource toResourceFromEntity(Geofence geofence) {
        return new GeofenceResource(geofence.getId(), geofence.getLatitude(), geofence.getLongitude(), geofence.getRadius(), geofence.getUser().getUsername());
    }
}
