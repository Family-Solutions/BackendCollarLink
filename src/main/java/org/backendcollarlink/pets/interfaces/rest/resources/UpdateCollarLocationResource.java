package org.backendcollarlink.pets.interfaces.rest.resources;

import org.backendcollarlink.pets.domain.model.aggregates.Collar;

public record UpdateCollarLocationResource(Long serialNumber, Double lastLatitude, Double lastLongitude) {
}
