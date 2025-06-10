package org.backendcollarlink.geofences.applications.internal.commandservices;

import org.backendcollarlink.geofences.domain.model.aggregates.Geofence;
import org.backendcollarlink.geofences.domain.model.commands.CreateGeofenceCommand;
import org.backendcollarlink.geofences.domain.model.commands.DeleteGeofenceCommand;
import org.backendcollarlink.geofences.domain.model.commands.UpdateGeofenceCommand;
import org.backendcollarlink.geofences.domain.services.GeofenceCommandService;
import org.backendcollarlink.geofences.infrastrucutre.persistence.jpa.repositories.GeofenceRepository;
import org.backendcollarlink.users.domain.model.aggregates.User;
import org.backendcollarlink.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GeofenceCommandServiceImpl implements GeofenceCommandService {
    private final UserRepository userRepository;
    private final GeofenceRepository geofenceRepository;

    public GeofenceCommandServiceImpl(UserRepository userRepository, GeofenceRepository geofenceRepository) {
        this.geofenceRepository = geofenceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Geofence> handle(CreateGeofenceCommand command) {
        Optional<User> user = userRepository.findByUsername(command.username());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        Geofence geofence = new Geofence(command.name(), command.longitude(), command.latitude(), command.radius(), user.get());
        geofenceRepository.save(geofence);
        return geofenceRepository.findById(geofence.getId());
    }

    @Override
    public Optional<Geofence> handle(UpdateGeofenceCommand command) {
        var result = geofenceRepository.findById(command.id());
        if (result.isEmpty()) {throw new IllegalArgumentException("Geofence not found");}
        var geofenceToUpdate = result.get();
        try {
            var updateGeofence = geofenceRepository.save(geofenceToUpdate.UpdateInformation(command.name(), command.latitude(), command.longitude(), command.radius()));
            return Optional.of(updateGeofence);
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while updating geofence: " + e.getMessage());
        }
    }

    @Override
    public Optional<Geofence> handle(DeleteGeofenceCommand command) {
        if(!geofenceRepository.existsById(command.id())){
            throw new IllegalArgumentException("Geofence not found");
        }
        try{
            var geofenceToDelete = geofenceRepository.findById(command.id());
            geofenceRepository.deleteById(command.id());
            return geofenceToDelete;
        }catch(Exception e){
            throw new IllegalArgumentException("Error while deleting geofence: " + e.getMessage());
        }
    }
}
