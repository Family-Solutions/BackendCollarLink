package org.backendcollarlink.pets.applications.internal.commandservices;

import org.backendcollarlink.pets.domain.model.aggregates.Collar;
import org.backendcollarlink.pets.domain.model.aggregates.Pet;
import org.backendcollarlink.pets.domain.model.commands.CreatePetCommand;
import org.backendcollarlink.pets.domain.model.commands.DeletePetCommand;
import org.backendcollarlink.pets.domain.model.commands.UpdatePetCollarCommand;
import org.backendcollarlink.pets.domain.model.commands.UpdatePetCommand;
import org.backendcollarlink.pets.domain.services.PetCommandService;
import org.backendcollarlink.pets.infrastructure.persistence.jpa.repositories.CollarRepository;
import org.backendcollarlink.pets.infrastructure.persistence.jpa.repositories.PetRepository;
import org.backendcollarlink.users.domain.model.aggregates.User;
import org.backendcollarlink.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetCommandServiceImpl implements PetCommandService {
    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final CollarRepository collarRepository;

    public PetCommandServiceImpl(PetRepository petRepository, UserRepository userRepository, CollarRepository collarRepository) {
        this.petRepository = petRepository;
        this.userRepository = userRepository;
        this.collarRepository = collarRepository;
    }

    @Override
    public Optional<Pet> handle(CreatePetCommand command) {
        Optional<User> user = userRepository.findByUsername(command.username());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        Optional<Collar> collar = collarRepository.findById(command.collarId());
        Pet pet = new Pet(user.get(), collar.get(), command.name(), command.species(), command.breed(), command.gender(), command.age());
        petRepository.save(pet);
        return petRepository.findById(pet.getId());
    }

    @Override
    public Optional<Pet> handle(UpdatePetCommand command) {
        var result = petRepository.findById(command.id());
        if (result.isEmpty()) {throw new IllegalArgumentException("Pet not found");}
        var petToUpdate = result.get();
        try{
            var updatePet = petRepository.save(petToUpdate.UpdateInformation(command.name(), command.species(), command.breed(), command.gender(), command.age()));
            return Optional.of(updatePet);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while updating pet: " + e.getMessage());
        }
    }

    @Override
    public Optional<Pet> handle(UpdatePetCollarCommand command) {
        var result = petRepository.findById(command.id());
        if (result.isEmpty()) {throw new IllegalArgumentException("Pet not found");}
        var collar = collarRepository.findById(command.collarId());
        if (collar.isEmpty()) {throw new IllegalArgumentException("Collar not found");}
        var petToUpdate = result.get();
        try{
            var updatePet = petRepository.save(petToUpdate.UpdateCollar(collar.get()));
            return Optional.of(updatePet);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while updating pet: " + e.getMessage());
        }
    }

    @Override
    public Optional<Pet> handle(DeletePetCommand command) {
        if(!petRepository.existsById(command.id())){
            throw new IllegalArgumentException("Pet not found");
        }
        try {
            var petToDelete = petRepository.findById(command.id());
            petRepository.deleteById(command.id());
            return petToDelete;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting pet: " + e.getMessage());
        }
    }
}
