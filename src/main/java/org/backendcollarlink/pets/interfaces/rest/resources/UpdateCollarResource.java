package org.backendcollarlink.pets.interfaces.rest.resources;

public record UpdateCollarResource(String username, String serialNumber, String model, Double lastLatitude, Double lastLongitude) {
}
