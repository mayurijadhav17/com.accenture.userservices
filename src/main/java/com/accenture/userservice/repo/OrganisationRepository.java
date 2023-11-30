package com.accenture.userservice.repo;

import com.accenture.userservice.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrganisationRepository extends JpaRepository<Organisation, Integer> {
Optional<Organisation> findById(UUID id);

boolean existsById(UUID id);

void deleteById(UUID id);
}
