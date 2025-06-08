package org.backendcollarlink.users.domain.services;

import org.backendcollarlink.users.domain.model.entities.Role;
import org.backendcollarlink.users.domain.model.queries.GetAllRolesQuery;
import org.backendcollarlink.users.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query);
    Optional<Role> handle(GetRoleByNameQuery query);
}
