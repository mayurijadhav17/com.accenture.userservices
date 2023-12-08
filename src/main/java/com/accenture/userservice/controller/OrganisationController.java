package com.accenture.userservice.controller;

import com.accenture.userservice.model.Organisation;
import com.accenture.userservice.service.OrganisationService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@NoArgsConstructor(force = true)
@RestController
@RequestMapping("/api/organisation")
public class OrganisationController {

private final OrganisationService organisationService;

@Autowired
public OrganisationController(OrganisationService organisationService) {
	this.organisationService = organisationService;
}
@PostMapping
public Organisation addOrganisation(@RequestBody Organisation organisation) throws Exception {
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
}

