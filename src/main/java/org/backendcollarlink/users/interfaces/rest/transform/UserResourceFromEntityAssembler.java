package org.backendcollarlink.users.interfaces.rest.transform;

import org.backendcollarlink.users.domain.model.aggregates.User;
import org.backendcollarlink.users.domain.model.entities.Role;
import org.backendcollarlink.users.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(user.getId(), user.getUsername(), roles);
    }
}
