package org.backendcollarlink.pets.domain.services;

import org.backendcollarlink.pets.domain.model.aggregates.Collar;
import org.backendcollarlink.pets.domain.model.queries.GetAllCollarsByUserUsernameQuery;
import org.backendcollarlink.pets.domain.model.queries.GetCollarByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CollarQueryService {
    Optional<Collar> handle(GetCollarByIdQuery command);
    List<Collar> handle(GetAllCollarsByUserUsernameQuery command);
}
