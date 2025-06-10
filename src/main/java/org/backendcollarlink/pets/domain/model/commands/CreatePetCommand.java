package org.backendcollarlink.pets.domain.model.commands;

public record CreatePetCommand(String username, Long collarId, String name, String species, String breed, String gender, int age) {
}
