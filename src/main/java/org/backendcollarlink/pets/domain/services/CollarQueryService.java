package org.backendcollarlink.pets.domain.services;

import org.backendcollarlink.pets.domain.model.aggregates.Collar;
import org.backendcollarlink.pets.domain.model.queries.GetCollarByIdQuery;
import org.backendcollarlink.pets.domain.model.queries.GetCollarByUserUsernameQuery;

import java.util.Optional;

public interface CollarQueryService {
    Optional<Collar> handle(GetCollarByIdQuery command);
    Optional<Collar> handle(GetCollarByUserUsernameQuery command);
}
