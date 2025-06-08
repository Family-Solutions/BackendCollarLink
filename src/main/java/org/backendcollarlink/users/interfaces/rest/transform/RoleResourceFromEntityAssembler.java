package org.backendcollarlink.users.interfaces.rest.transform;

import org.backendcollarlink.users.domain.model.entities.Role;
import org.backendcollarlink.users.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}