package org.backendcollarlink.geofences.interfaces.rest.resources;

public record UpdateGeofenceResource(String name, Double latitude, Double longitude, Double radius) {
}
