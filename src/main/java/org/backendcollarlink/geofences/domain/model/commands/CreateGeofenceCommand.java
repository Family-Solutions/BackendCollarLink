package org.backendcollarlink.geofences.domain.model.commands;

import org.backendcollarlink.users.domain.model.aggregates.User;

public record CreateGeofenceCommand(Double latitude, Double longitude, Double radius, String username) {
}
