package org.backendcollarlink.pets.domain.services;

import org.backendcollarlink.pets.domain.model.aggregates.Collar;
import org.backendcollarlink.pets.domain.model.commands.CreateCollarCommand;
import org.backendcollarlink.pets.domain.model.commands.DeleteCollarCommand;
import org.backendcollarlink.pets.domain.model.commands.UpdateCollarCommand;
import org.backendcollarlink.pets.domain.model.commands.UpdateCollarLocationCommand;

import java.util.Optional;

public interface CollarCommandService {
    Optional<Collar> handle(CreateCollarCommand command);
    Optional<Collar> handle(UpdateCollarCommand command);
    Optional<Collar> handle(DeleteCollarCommand command);
    Optional<Collar> handle(UpdateCollarLocationCommand command);
}
