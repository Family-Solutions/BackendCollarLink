package org.backendcollarlink.users.domain.model.queries;

import org.backendcollarlink.users.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
