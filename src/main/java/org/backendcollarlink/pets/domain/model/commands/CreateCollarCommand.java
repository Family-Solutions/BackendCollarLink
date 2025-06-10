package org.backendcollarlink.pets.domain.model.commands;

public record CreateCollarCommand(Long petId, Long serialNumber, String model) {
}
