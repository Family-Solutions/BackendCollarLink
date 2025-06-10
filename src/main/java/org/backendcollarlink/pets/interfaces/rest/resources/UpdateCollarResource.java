package org.backendcollarlink.pets.interfaces.rest.resources;

public record UpdateCollarResource(Long petId, Long serialNumber, String model) {
}
