package org.backendcollarlink.pets.interfaces.rest.resources;

public record UpdatePetResource(String name, String species, String breed, String gender, int age) {
}
