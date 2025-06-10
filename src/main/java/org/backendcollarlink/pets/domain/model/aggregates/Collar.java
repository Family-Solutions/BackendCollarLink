package org.backendcollarlink.pets.domain.model.aggregates;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.backendcollarlink.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Getter
@Setter
@Entity
public class Collar extends AuditableAbstractAggregateRoot<Collar> {
    @Nullable
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @NotNull
    private Long serialNumber;

    @NotBlank
    private String model;

    public Collar(Pet pet, Long serialNumber, String model) {
        this.pet = pet;
        this.serialNumber = serialNumber;
        this.model = model;
    }

    public Collar UpdateInformation(Pet pet, Long serialNumber, String model) {
        this.pet = pet;
        this.serialNumber = serialNumber;
        this.model = model;
        return this;
    }

    public Collar() {}
}
