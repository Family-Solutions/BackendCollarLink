package org.backendcollarlink.users.domain.services;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.backendcollarlink.users.domain.model.aggregates.User;
import org.backendcollarlink.users.domain.model.commands.DeleteUserCommand;
import org.backendcollarlink.users.domain.model.commands.SignInCommand;
import org.backendcollarlink.users.domain.model.commands.SignUpCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
    Optional<User> handle(SignUpCommand command);
    Optional<User> handle(DeleteUserCommand command);
}
