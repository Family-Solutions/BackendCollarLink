package org.backendcollarlink.geofences.domain.model.commands;

public record UpdateGeofenceCommand(Long id, Double latitude, Double longitude, Double radius) {
}
