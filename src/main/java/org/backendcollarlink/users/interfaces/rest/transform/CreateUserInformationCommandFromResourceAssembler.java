package org.backendcollarlink.users.interfaces.rest.transform;

import org.backendcollarlink.users.domain.model.commands.CreateUserInformationCommand;
import org.backendcollarlink.users.interfaces.rest.resources.CreateUserInformationResource;

public class CreateUserInformationCommandFromResourceAssembler {
    public static CreateUserInformationCommand toCommandfromResource(CreateUserInformationResource resource) {
        return new CreateUserInformationCommand(resource.firstName(), resource.lastName(), resource.phoneNumber(), resource.photo(), resource.email(), resource.userId());
    }
}