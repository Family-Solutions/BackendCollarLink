package org.backendcollarlink.pets.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

public record CreateCollarResource(Long petId,
                                   @NotNull
                                   Long serialNumber,
                                   @NotNull
                                   String model) {
}
