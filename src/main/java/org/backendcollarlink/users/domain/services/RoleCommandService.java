package org.backendcollarlink.users.domain.services;

import org.backendcollarlink.users.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
