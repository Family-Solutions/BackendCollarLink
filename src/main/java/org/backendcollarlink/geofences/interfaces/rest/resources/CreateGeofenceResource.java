package org.backendcollarlink.geofences.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;
import org.backendcollarlink.geofences.domain.model.aggregates.Geofence;

public record CreateGeofenceResource(@NotNull
                                     Double latitude,
                                     @NotNull
                                     Double longitude,
                                     @NotNull
                                     Double radius,
                                     @NotNull
                                     String username) {
}
