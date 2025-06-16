package org.backendcollarlink.pets.domain.model.commands;

public record UpdateCollarLocationCommand(Long serialNumber, Double lastLatitude, Double lastLongitude) {
}
