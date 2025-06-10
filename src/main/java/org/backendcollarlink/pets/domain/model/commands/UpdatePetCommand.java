package org.backendcollarlink.pets.domain.model.commands;

public record UpdatePetCommand(Long id, String name, String species, String breed, String gender, int age) {
}
