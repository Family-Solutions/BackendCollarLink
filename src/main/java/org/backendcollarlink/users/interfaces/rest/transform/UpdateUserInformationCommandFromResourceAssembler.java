package org.backendcollarlink.users.interfaces.rest.transform;

import org.backendcollarlink.users.domain.model.commands.UpdateUserInformationCommand;
import org.backendcollarlink.users.interfaces.rest.resources.UpdateUserInformationResource;

public class UpdateUserInformationCommandFromResourceAssembler {
    public static UpdateUserInformationCommand toCommandfromResource(Long userId, UpdateUserInformationResource resource) {
        return new UpdateUserInformationCommand(userId, resource.firstName(), resource.lastName(), resource.phoneNumber(), resource.photo(), resource.email());
    }
}
