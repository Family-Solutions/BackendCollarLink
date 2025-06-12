package org.backendcollarlink.pets.domain.model.commands;

public record UpdateCollarCommand(Long id, String username, Long serialNumber, String model, Double lastLatitude, Double lastLongitude) {
}
