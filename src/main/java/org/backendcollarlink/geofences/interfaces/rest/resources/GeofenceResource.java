package org.backendcollarlink.geofences.interfaces.rest.resources;

public record GeofenceResource(Long id,
                               Double latitude,
                               Double longitude,
                               Double radius,
                               String username) {
}
