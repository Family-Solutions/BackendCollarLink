package org.backendcollarlink.pets.applications.internal.queryservices;

import org.backendcollarlink.pets.domain.model.aggregates.Collar;
import org.backendcollarlink.pets.domain.model.queries.GetCollarByIdQuery;
import org.backendcollarlink.pets.domain.model.queries.GetCollarByUserUsernameQuery;
import org.backendcollarlink.pets.domain.services.CollarQueryService;
import org.backendcollarlink.pets.infrastructure.persistence.jpa.repositories.CollarRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CollarQueryServiceImpl implements CollarQueryService {
    private final CollarRepository collarRepository;

    public CollarQueryServiceImpl(CollarRepository collarRepository) {
        this.collarRepository = collarRepository;
    }

    @Override
    public Optional<Collar> handle(GetCollarByIdQuery query){
        return collarRepository.findById(query.id());
    }

    @Override
    public Optional<Collar> handle(GetCollarByUserUsernameQuery query){
        return collarRepository.findByUserUsername(query.username());
    }
}
