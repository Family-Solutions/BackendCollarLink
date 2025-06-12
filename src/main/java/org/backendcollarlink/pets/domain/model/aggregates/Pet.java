package org.backendcollarlink.pets.domain.model.aggregates;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.backendcollarlink.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import org.backendcollarlink.users.domain.model.aggregates.User;

@Getter
@Setter
@Entity
public class Pet extends AuditableAbstractAggregateRoot<Pet> {
    @Getter
    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_username", referencedColumnName = "username")
    private User user;

    @Nullable
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collar_id")
    private Collar collar;

    @NotBlank
    private String name;

    @NotBlank
    private String species;

    @NotBlank
    private String breed;

    @NotBlank
    private String gender;

    @NotBlank
    private int age;

    public Pet(User user, Collar collar, String name, String species, String breed, String gender, int age) {
        this.user = user;
        this.collar = collar;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
    }

    public Pet UpdateInformation(String name, String species, String breed, String gender, int age) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
        return this;
    }

    public Pet UpdateCollar(Collar collar) {
        this.collar = collar;
        return this;
    }

    public Pet() {}
}
