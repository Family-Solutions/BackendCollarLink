package org.backendcollarlink.pets.interfaces.rest.transform;

import org.backendcollarlink.pets.domain.model.aggregates.Pet;
import org.backendcollarlink.pets.interfaces.rest.resources.PetResource;

public class PetResourceFromEntityAssembler {
    public static PetResource toResourceFromEntity(Pet pet) {
        return new PetResource(pet.getId(), pet.getUser().getUsername(), pet.getCollar().getId(), pet.getName(), pet.getSpecies(), pet.getBreed(), pet.getGender(), pet.getAge());
    }
}
