package com.accenture.userservice.controller;

import com.accenture.userservice.model.Organisation;
import com.accenture.userservice.service.OrganisationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/organisation")
public class OrganisationController {
  
  private final OrganisationService organisationService;
  
  
  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public Organisation addOrganisation(@RequestBody @Valid Organisation organisation) throws Exception {
    return organisationService.create(organisation);
  }
  
  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public Organisation getOrganisationById(@PathVariable Long id) {
    return organisationService.getOrganisationById(id);
  }
  
  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public List<Organisation> getAllOrganisation() {
    return organisationService.getAllOrganisations();
  }
  
 
  @DeleteMapping("{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void deleteOrganisation(@PathVariable Long id) {
    organisationService.deleteById(id);
  }
  

  @PutMapping("{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public Organisation updateOrganisation(@RequestBody @Valid Organisation organisation, @PathVariable Long id) throws Exception {
    return organisationService.updateOrganisationDetails(organisation, id);
  }
}

