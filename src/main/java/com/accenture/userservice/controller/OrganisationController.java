package com.accenture.userservice.controller;

import com.accenture.userservice.model.Organisation;
import com.accenture.userservice.service.OrganisationService;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor(force = true)
@RestController
@RequestMapping("/api/organisation")
public class OrganisationController {

private final OrganisationService organisationService;

@PostMapping
public Organisation addOrganisation(@RequestBody Organisation organisation) {
	return organisationService.create(organisation);
}

@GetMapping("/{id}")
public Organisation getOrganisationById(@PathVariable UUID id) {
	return organisationService.getOrganisationById(id);
}

@GetMapping
public List<Organisation> getAllOrganisation() {
	return organisationService.getAllOrganisations();
}

@DeleteMapping("{id}")
public void deleteOrganisation(@PathVariable UUID id) {
	organisationService.deleteById(id);
}
}

