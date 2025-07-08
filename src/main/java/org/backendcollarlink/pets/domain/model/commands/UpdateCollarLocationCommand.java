package org.backendcollarlink.pets.domain.model.commands;

public record UpdateCollarLocationCommand(String serialNumber, Double lastLatitude, Double lastLongitude) {
}
