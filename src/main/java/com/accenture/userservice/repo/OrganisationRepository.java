package com.accenture.userservice.repo;

import com.accenture.userservice.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {
  
  boolean existsById(Long id);
  
  Optional<Organisation> findByDomain(String domain);
  
  boolean existsByDomain(String domain);
  
}
