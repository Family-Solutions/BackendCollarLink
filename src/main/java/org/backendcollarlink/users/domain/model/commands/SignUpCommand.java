package org.backendcollarlink.users.domain.model.commands;

import org.backendcollarlink.users.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String username, String password, List<Role> roles) {
}
