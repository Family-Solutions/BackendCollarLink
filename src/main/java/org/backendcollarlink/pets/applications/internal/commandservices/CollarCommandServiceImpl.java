package org.backendcollarlink.pets.applications.internal.commandservices;

import org.backendcollarlink.pets.domain.model.aggregates.Collar;
import org.backendcollarlink.pets.domain.model.aggregates.Pet;
import org.backendcollarlink.pets.domain.model.commands.CreateCollarCommand;
import org.backendcollarlink.pets.domain.model.commands.DeleteCollarCommand;
import org.backendcollarlink.pets.domain.model.commands.UpdateCollarCommand;
import org.backendcollarlink.pets.domain.services.CollarCommandService;
import org.backendcollarlink.pets.infrastructure.persistence.jpa.repositories.CollarRepository;
import org.backendcollarlink.pets.infrastructure.persistence.jpa.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CollarCommandServiceImpl implements CollarCommandService {
    private final CollarRepository collarRepository;
    private final PetRepository petRepository;

    public CollarCommandServiceImpl(CollarRepository collarRepository, PetRepository petRepository) {
        this.collarRepository = collarRepository;
        this.petRepository = petRepository;
    }

    @Override
    public Optional<Collar> handle(CreateCollarCommand command) {
        Optional<Pet> pet = petRepository.findById(command.petId());
        Collar collar = new Collar(pet.get(), command.serialNumber(), command.model());
        collarRepository.save(collar);
        return collarRepository.findById(collar.getId());
    }

    @Override
    public Optional<Collar> handle(UpdateCollarCommand command) {
        var result = collarRepository.findById(command.id());
        if (result.isEmpty()) {throw new IllegalArgumentException("Collar not found");}
        var pet = petRepository.findById(command.petId());
        var collarToUpdate = result.get();
        try{
            var updateCollar = collarRepository.save(collarToUpdate.UpdateInformation(pet.get(), command.serialNumber(), command.model()));
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
}
