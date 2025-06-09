package org.backendcollarlink.geofences.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private User user;

    @NotBlank
    private double latitude;

    @NotBlank
    private double longitude;

    @NotBlank
    private double radius;

    public Geofence(double latitude, double longitude, double radius, User user) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.user = user;
    }

    public Geofence UpdateInformation(double latitude, double longitude, double radius) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        return this;
    }

    public Geofence() {}

}
