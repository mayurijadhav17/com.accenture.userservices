package com.accenture.userservice.service;

import com.accenture.userservice.exception.ResourceNotFoundException;
import com.accenture.userservice.model.Organisation;
import com.accenture.userservice.repo.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisationService {
private final OrganisationRepository organisationRepository;

@Autowired
public OrganisationService(OrganisationRepository organisationRepository) {
	this.organisationRepository = organisationRepository;
}
public Organisation create(Organisation organisation)	{
	return organisationRepository.save(organisation);
}

public Organisation getOrganisationById(Long id) {
	return organisationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Organisation Not found with id = " + id));
}

public List<Organisation> getAllOrganisations() {
	return organisationRepository.findAll();
}

public void deleteById(Long id) {
	if (!organisationRepository.existsById(id)) {
		throw new ResourceNotFoundException("Organisation Not Found for id " + id);
	}
	organisationRepository.deleteById(id);
}

}
