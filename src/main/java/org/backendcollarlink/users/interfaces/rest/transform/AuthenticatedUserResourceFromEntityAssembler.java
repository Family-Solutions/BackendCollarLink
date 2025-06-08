package org.backendcollarlink.users.interfaces.rest.transform;

import org.backendcollarlink.users.domain.model.aggregates.User;
import org.backendcollarlink.users.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}