package org.backendcollarlink.pets.applications.internal.commandservices;

import org.backendcollarlink.pets.domain.model.aggregates.Collar;
import org.backendcollarlink.pets.domain.model.commands.CreateCollarCommand;
import org.backendcollarlink.pets.domain.model.commands.DeleteCollarCommand;
import org.backendcollarlink.pets.domain.model.commands.UpdateCollarCommand;
import org.backendcollarlink.pets.domain.model.commands.UpdateCollarLocationCommand;
import org.backendcollarlink.pets.domain.services.CollarCommandService;
import org.backendcollarlink.pets.infrastructure.persistence.jpa.repositories.CollarRepository;
import org.backendcollarlink.users.domain.model.aggregates.User;
import org.backendcollarlink.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CollarCommandServiceImpl implements CollarCommandService {
    private final CollarRepository collarRepository;
    private final UserRepository userRepository;

    public CollarCommandServiceImpl(CollarRepository collarRepository, UserRepository userRepository) {
        this.collarRepository = collarRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Collar> handle(CreateCollarCommand command) {
        Optional<User> user = userRepository.findByUsername(command.username());
        Collar collar = new Collar(user.get(), command.serialNumber(), command.model(), command.lastLatitude(), command.lastLongitude());
        collarRepository.save(collar);
        return collarRepository.findById(collar.getId());
    }

    @Override
    public Optional<Collar> handle(UpdateCollarCommand command) {
        var result = collarRepository.findById(command.id());
        if (result.isEmpty()) {throw new IllegalArgumentException("Collar not found");}
        var user = userRepository.findByUsername(command.username());
        var collarToUpdate = result.get();
        try{
            var updateCollar = collarRepository.save(collarToUpdate.UpdateInformation(user.get(), command.serialNumber(), command.model(), command.lastLatitude(), command.lastLongitude()));
            return Optional.of(updateCollar);
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while updating collar: " + e.getMessage());
        }
    }

    @Override
    public Optional<Collar> handle(DeleteCollarCommand command) {
        if(!collarRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Collar not found");
        }
        try{
            var collarToDelete = collarRepository.findById(command.id());
            collarRepository.deleteById(command.id());
            return collarToDelete;
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting collar: " + e.getMessage());
        }
    }

    @Override
    public Optional<Collar> handle(UpdateCollarLocationCommand command){
        var result = collarRepository.findBySerialNumber(command.serialNumber());
        if (result.isEmpty()) {throw new IllegalArgumentException("Collar serial number not found");}
        var collarLocationToUpdate = result.get();
        try{
            var updateCollar = collarRepository.save(collarLocationToUpdate.UpdateLocation(command.lastLatitude(), command.lastLongitude()));
            return Optional.of(updateCollar);
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while updating collar: " + e.getMessage());
        }
    }
}
