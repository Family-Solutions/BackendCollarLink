package org.backendcollarlink.pets.interfaces.rest.resources;

public record CollarResource(Long id,
                             Long petId,
                             Long serialNumber,
                             String model) {
}
