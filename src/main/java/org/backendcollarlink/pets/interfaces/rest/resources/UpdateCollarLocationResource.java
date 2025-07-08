package org.backendcollarlink.pets.interfaces.rest.resources;

import org.backendcollarlink.pets.domain.model.aggregates.Collar;

public record UpdateCollarLocationResource(String serialNumber, Double lastLatitude, Double lastLongitude) {
}
