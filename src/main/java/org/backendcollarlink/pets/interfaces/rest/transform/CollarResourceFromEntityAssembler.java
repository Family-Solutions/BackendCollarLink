package org.backendcollarlink.pets.interfaces.rest.transform;

import org.backendcollarlink.pets.domain.model.aggregates.Collar;
import org.backendcollarlink.pets.interfaces.rest.resources.CollarResource;

public class CollarResourceFromEntityAssembler {
    public static CollarResource toResourceFromEntity(Collar collar) {
        return new CollarResource(collar.getId(), collar.getUser().getUsername(), collar.getSerialNumber(), collar.getModel(), collar.getLastLatitude(), collar.getLastLongitude());
    }
}
