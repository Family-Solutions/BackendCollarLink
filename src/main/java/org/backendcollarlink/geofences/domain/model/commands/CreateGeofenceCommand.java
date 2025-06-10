package org.backendcollarlink.geofences.domain.model.commands;

import org.backendcollarlink.users.domain.model.aggregates.User;

public record CreateGeofenceCommand(String name, Double latitude, Double longitude, Double radius, String username) {
}
