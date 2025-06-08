package org.backendcollarlink.users.infrastructure.persistence.jpa.repositories;

import org.backendcollarlink.users.domain.model.aggregates.UserInformation;
import org.backendcollarlink.users.domain.model.valueobjects.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {
    Optional<UserInformation> findByEmail(EmailAddress emailAddress);
    Optional<UserInformation> findByUserId(Long userId);
}
