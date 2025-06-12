package org.backendcollarlink.pets.interfaces.rest.resources;

public record UpdateCollarResource(String username, Long serialNumber, String model, Double lastLatitude, Double lastLongitude) {
}
