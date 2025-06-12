package org.backendcollarlink.pets.domain.model.commands;

public record CreateCollarCommand(String username, Long serialNumber, String model, Double lastLatitude, Double lastLongitude) {
}
