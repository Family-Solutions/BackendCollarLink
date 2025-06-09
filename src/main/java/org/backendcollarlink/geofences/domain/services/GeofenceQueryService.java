package org.backendcollarlink.geofences.domain.services;

import org.backendcollarlink.geofences.domain.model.aggregates.Geofence;
import org.backendcollarlink.geofences.domain.model.queries.GetAllGeofencesByUsernameQuery;
import org.backendcollarlink.geofences.domain.model.queries.GetGeofenceByIdQuery;

import java.util.List;
import java.util.Optional;

public interface GeofenceQueryService {
    List<Geofence> handle(GetAllGeofencesByUsernameQuery query);
    Optional<Geofence> handle(GetGeofenceByIdQuery query);
}
