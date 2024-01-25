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
@PreAuthorize("hasRole('ADMIN')")
public class OrganisationController {
  
  private final OrganisationService organisationService;
  
  @PostMapping
  public Organisation addOrganisation(@RequestBody @Valid Organisation organisation) throws Exception {
    return organisationService.create(organisation);
  }
  
  @GetMapping("/{id}")
  public Organisation getOrganisationById(@PathVariable Long id) {
    return organisationService.getOrganisationById(id);
  }
  
  @GetMapping
  public List<Organisation> getAllOrganisation() {
    return organisationService.getAllOrganisations();
  }
  
  @DeleteMapping("{id}")
  public void deleteOrganisation(@PathVariable Long id) {
    organisationService.deleteById(id);
  }
  
  @PutMapping("{id}")
  public Organisation updateOrganisation(@RequestBody @Valid Organisation organisation, @PathVariable Long id) throws Exception {
    return organisationService.updateOrganisationDetails(organisation, id);
  }
}

