package org.backendcollarlink.geofences.interfaces.rest.resources;

public record GeofenceResource(Long id,
                               String name,
                               Double latitude,
                               Double longitude,
                               Double radius,
                               String username) {
}
