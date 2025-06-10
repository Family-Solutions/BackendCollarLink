package org.backendcollarlink.pets.domain.model.commands;

public record UpdateCollarCommand(Long id, Long petId, Long serialNumber, String model) {
}
