package org.backendcollarlink.users.application.internal.queryservices;

import org.backendcollarlink.users.domain.model.aggregates.UserInformation;
import org.backendcollarlink.users.domain.model.queries.GetAllUsersInformationQuery;
import org.backendcollarlink.users.domain.model.queries.GetUserInformationByIdQuery;
import org.backendcollarlink.users.domain.model.queries.GetUserInformationByUserIdQuery;
import org.backendcollarlink.users.domain.services.UserInformationQueryService;
import org.backendcollarlink.users.infrastructure.persistence.jpa.repositories.UserInformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInformationQueryServiceImpl implements UserInformationQueryService {

    private final UserInformationRepository userInformationRepository;

    public UserInformationQueryServiceImpl(UserInformationRepository userInformationRepository) {
        this.userInformationRepository = userInformationRepository;
    }

    @Override
    public List<UserInformation> handle(GetAllUsersInformationQuery query) {
        return userInformationRepository.findAll();
    }

    @Override
    public Optional<UserInformation> handle(GetUserInformationByIdQuery query) {
        return userInformationRepository.findById(query.userId());
    }

    @Override
    public Optional<UserInformation> handle(GetUserInformationByUserIdQuery query) {
        return userInformationRepository.findByUserId(query.id());
    }
}