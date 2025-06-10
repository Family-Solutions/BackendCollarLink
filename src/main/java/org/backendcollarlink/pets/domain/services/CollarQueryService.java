package org.backendcollarlink.pets.domain.services;

import org.backendcollarlink.pets.domain.model.aggregates.Collar;
import org.backendcollarlink.pets.domain.model.queries.GetCollarByIdQuery;
import org.backendcollarlink.pets.domain.model.queries.GetCollarByPetIdQuery;

import java.util.Optional;

public interface CollarQueryService {
    Optional<Collar> handle(GetCollarByIdQuery command);
    Optional<Collar> handle(GetCollarByPetIdQuery command);
}
