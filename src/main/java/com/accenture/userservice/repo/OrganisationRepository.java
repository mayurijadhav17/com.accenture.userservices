package com.accenture.userservice.repo;

import com.accenture.userservice.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganisationRepository extends JpaRepository<Organisation, Integer> {
Optional<Organisation> findById(Long id);

boolean existsById(Long id);

void deleteById(Long id);
}
