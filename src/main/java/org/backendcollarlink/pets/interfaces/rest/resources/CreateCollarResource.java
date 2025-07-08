package org.backendcollarlink.pets.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

public record CreateCollarResource(String username,
                                   @NotNull
                                   String serialNumber,
                                   @NotNull
                                   String model,
                                   Double lastLatitude,
                                   Double lastLongitude) {
}
