package org.backendcollarlink.pets.domain.model.aggregates;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.backendcollarlink.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import org.backendcollarlink.users.domain.model.aggregates.User;

@Getter
@Setter
@Entity
public class Collar extends AuditableAbstractAggregateRoot<Collar> {
    @Nullable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_username")
    private User user;

    @NotNull
    private String serialNumber;

    @NotBlank
    private String model;

    private Double lastLatitude;

    private Double lastLongitude;

    public Collar(User user, String serialNumber, String model, Double lastLatitude, Double lastLongitude) {
        this.user = user;
        this.serialNumber = serialNumber;
        this.model = model;
        this.lastLatitude = lastLatitude;
        this.lastLongitude = lastLongitude;
    }

    public Collar UpdateInformation(User user, String serialNumber, String model, Double lastLatitude, Double lastLongitude) {
        this.user = user;
        this.serialNumber = serialNumber;
        this.model = model;
        this.lastLatitude = lastLatitude;
        this.lastLongitude = lastLongitude;
        return this;
    }

    public Collar UpdateLocation(Double lastLatitude, Double lastLongitude) {
        this.lastLatitude = lastLatitude;
        this.lastLongitude = lastLongitude;
        return this;
    }

    public Collar() {}
}
