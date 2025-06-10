package org.backendcollarlink.pets.domain.model.aggregates;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.backendcollarlink.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Getter
@Setter
@Entity
public class Collar extends AuditableAbstractAggregateRoot<Collar> {

}
