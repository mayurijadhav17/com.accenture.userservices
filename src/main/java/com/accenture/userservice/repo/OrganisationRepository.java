package com.accenture.userservice.repo;

import com.accenture.userservice.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {
  Optional<Organisation> findById(Long id);
  
  boolean existsById(Long id);
  
  Optional<Organisation> findByDomain(String domain);
  
  boolean existsByDomain(String domain);
  
  void deleteById(Long id);
}
