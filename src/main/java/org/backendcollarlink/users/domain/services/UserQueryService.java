package org.backendcollarlink.users.domain.services;

import org.backendcollarlink.users.domain.model.aggregates.User;
import org.backendcollarlink.users.domain.model.queries.GetAllUsersQuery;
import org.backendcollarlink.users.domain.model.queries.GetUserByIdQuery;
import org.backendcollarlink.users.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> handle(GetUserByUsernameQuery query);
}