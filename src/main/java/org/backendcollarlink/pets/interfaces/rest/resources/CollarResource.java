package org.backendcollarlink.pets.interfaces.rest.resources;

public record CollarResource(Long id,
                             String username,
                             String serialNumber,
                             String model,
                             Double lastLatitude,
                             Double lastLongitude) {
}
