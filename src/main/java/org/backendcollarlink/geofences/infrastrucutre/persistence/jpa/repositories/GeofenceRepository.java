package org.backendcollarlink.geofences.infrastrucutre.persistence.jpa.repositories;

import org.backendcollarlink.geofences.domain.model.aggregates.Geofence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GeofenceRepository extends JpaRepository<Geofence, Long> {
    List<Geofence> findAllByUserUsername(String username);
}
