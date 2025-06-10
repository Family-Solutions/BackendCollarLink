package org.backendcollarlink.geofences.domain.model.commands;

public record UpdateGeofenceCommand(Long id, String name, Double latitude, Double longitude, Double radius) {
}
