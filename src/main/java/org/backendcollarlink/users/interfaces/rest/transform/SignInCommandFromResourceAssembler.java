package org.backendcollarlink.users.interfaces.rest.transform;

import org.backendcollarlink.users.domain.model.commands.SignInCommand;
import org.backendcollarlink.users.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}