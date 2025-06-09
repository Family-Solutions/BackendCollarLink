package org.backendcollarlink.geofences.applications.internal.queryservices;

import org.backendcollarlink.geofences.domain.model.aggregates.Geofence;
import org.backendcollarlink.geofences.domain.model.queries.GetAllGeofencesByUsernameQuery;
import org.backendcollarlink.geofences.domain.model.queries.GetGeofenceByIdQuery;
import org.backendcollarlink.geofences.domain.services.GeofenceQueryService;
import org.backendcollarlink.geofences.infrastrucutre.persistence.jpa.repositories.GeofenceRepository;

import java.util.List;
import java.util.Optional;

public class GeofenceQueryServiceImpl implements GeofenceQueryService {
    private final GeofenceRepository geofenceRepository;

    public GeofenceQueryServiceImpl(GeofenceRepository geofenceRepository) {
        this.geofenceRepository = geofenceRepository;
    }

    @Override
    public List<Geofence> handle(GetAllGeofencesByUsernameQuery query) {
        return geofenceRepository.findAllByUsername(query.username());
    }

    @Override
    public Optional<Geofence> handle(GetGeofenceByIdQuery query) {
        return geofenceRepository.findById(query.geofenceId());
    }
}
