package com.accenture.userservice.service;

import com.accenture.userservice.exception.OrganisationDomainAlreadyExistException;
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
  
 // private final RestTemplate restTemplate;
  
  public Organisation create(Organisation organisation) throws Exception {
    if(organisationRepository.existsByDomain(organisation.getDomain())) {
      throw new OrganisationDomainAlreadyExistException("ORGANISATION_EXISTS" + organisation.getDomain());
    }
    return organisationRepository.save(organisation);
  }
  
  public Organisation getOrganisationById(Long id) {
    return organisationRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("ORGANISATION_NOT_FOUND" + id));
  }
  
  public List<Organisation> getAllOrganisations() {
//    UriComponentsBuilder uriBuilder = UriComponentsBuilder
//            .fromUriString("http://localhost:8080/api/organisation")
//            .queryParam("limit", String.valueOf(10));
//    return Collections.singletonList(restTemplate.getForObject(uriBuilder.toUriString(), Organisation.class));
//
    return organisationRepository.findAll();
  }
  
  public void deleteById(Long id) {
    if(!organisationRepository.existsById(id)) {
      throw new ResourceNotFoundException("ORGANISATION_INVALID" + id);
    }
    organisationRepository.deleteById(id);
  }
  
  public Organisation updateOrganisationDetails(Organisation organisationRequest, Long id) throws Exception {
    Organisation organisation = organisationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("ORGANISATION_NOT_FOUND " + id));
    if(organisationRepository.existsByDomain(organisationRequest.getDomain())) {
      throw new OrganisationDomainAlreadyExistException("ORGANISATION_EXISTS" + organisationRequest.getDomain());
    }
    organisation.setName(organisationRequest.getName());
    organisation.setDomain(organisationRequest.getDomain());
    return organisationRepository.save(organisation);
  }
}
