package org.backendcollarlink.geofences.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.backendcollarlink.geofences.domain.model.commands.CreateGeofenceCommand;
import org.backendcollarlink.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import org.backendcollarlink.users.domain.model.aggregates.User;

@Getter
@Setter
@Entity
public class Geofence extends AuditableAbstractAggregateRoot<Geofence> {
    @Getter
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @NotBlank
    private String name;

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    @NotNull
    private double radius;

    public Geofence(String name, double latitude, double longitude, double radius, User user) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.user = user;
    }

    public Geofence UpdateInformation(String name, double latitude, double longitude, double radius) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        return this;
    }

    public Geofence() {}

}
