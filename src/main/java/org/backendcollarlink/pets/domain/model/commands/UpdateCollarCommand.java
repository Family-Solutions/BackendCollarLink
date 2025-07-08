package org.backendcollarlink.pets.domain.model.commands;

public record UpdateCollarCommand(Long id, String username, String serialNumber, String model, Double lastLatitude, Double lastLongitude) {
}
