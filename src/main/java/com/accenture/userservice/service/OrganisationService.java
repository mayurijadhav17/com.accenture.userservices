package com.accenture.userservice.service;

import com.accenture.userservice.exception.ResourceNotFoundException;
import com.accenture.userservice.model.Organisation;
import com.accenture.userservice.repo.OrganisationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrganisationService {
  private final OrganisationRepository organisationRepository;
  
  public Organisation create(Organisation organisation) throws Exception {
    if(organisationRepository.existsByDomain(organisation.getDomain())) {
      throw new Exception("Organisation with domain is already exist!! " + organisation.getDomain());
    }
    return organisationRepository.save(organisation);
  }
  
  public Organisation getOrganisationById(Long id) {
    return organisationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Organisation Not found with id = " + id));
  }
  
  public List<Organisation> getAllOrganisations() {
    return organisationRepository.findAll();
  }
  
  public void deleteById(Long id) {
    if(!organisationRepository.existsById(id)) {
      throw new ResourceNotFoundException("Organisation Not Found for id " + id);
    }
    organisationRepository.deleteById(id);
  }
  
}
