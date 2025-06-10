package org.backendcollarlink.pets.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

public record CreatePetResource(@NotNull
                                String username,
                                Long collarId,
                                @NotNull
                                String name,
                                @NotNull
                                String species,
                                @NotNull
                                String breed,
                                @NotNull
                                String gender,
                                @NotNull
                                int age) {
}
