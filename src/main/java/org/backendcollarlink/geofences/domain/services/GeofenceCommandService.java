package org.backendcollarlink.geofences.domain.services;

import org.backendcollarlink.geofences.domain.model.aggregates.Geofence;
import org.backendcollarlink.geofences.domain.model.commands.CreateGeofenceCommand;
import org.backendcollarlink.geofences.domain.model.commands.DeleteGeofenceCommand;
import org.backendcollarlink.geofences.domain.model.commands.UpdateGeofenceCommand;

import java.util.Optional;

public interface GeofenceCommandService {
    Optional<Geofence> handle(CreateGeofenceCommand command);
    Optional<Geofence> handle(UpdateGeofenceCommand command);
    Optional<Geofence> handle(DeleteGeofenceCommand command);
}
