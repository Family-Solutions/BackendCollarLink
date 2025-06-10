package org.backendcollarlink.pets.interfaces.rest.resources;

public record PetResource(Long id,
                          String username,
                          Long collarId,
                          String name,
                          String species,
                          String breed,
                          String gender,
                          int age) {
}
