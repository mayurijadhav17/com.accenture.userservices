package com.accenture.userservice.service;

import com.accenture.userservice.exception.ServiceRuntimeException;
import com.accenture.userservice.model.ErrorCodeEnum;
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
        if (organisationRepository.existsByDomain(organisation.getDomain())) {
            throw new ServiceRuntimeException(ErrorCodeEnum.ORGANISATION_DOMAIN_EXISTS,ErrorCodeEnum.ORGANISATION_DOMAIN_EXISTS.getTemplate(),organisation.getDomain());
        }
        return organisationRepository.save(organisation);
    }

    public Organisation getOrganisationById(Long id) {
        return organisationRepository.findById(id).
                orElseThrow(() -> new ServiceRuntimeException(ErrorCodeEnum.ORGANISATION_NOT_FOUND,ErrorCodeEnum.ORGANISATION_NOT_FOUND.getTemplate(),id.toString()));
    }

    public List<Organisation> getAllOrganisations() {
        return organisationRepository.findAll();
    }

    public void deleteById(Long id) {
        if (!organisationRepository.existsById(id)) {
            throw new ServiceRuntimeException(ErrorCodeEnum.ORGANISATION_NOT_FOUND,ErrorCodeEnum.ORGANISATION_NOT_FOUND.getTemplate(),id.toString());
        }
        organisationRepository.deleteById(id);
    }

    public Organisation updateOrganisationDetails(Organisation organisationRequest, Long id) throws Exception {
        Organisation organisation = organisationRepository.findById(id)
                .orElseThrow(() -> new ServiceRuntimeException(ErrorCodeEnum.ORGANISATION_NOT_FOUND,ErrorCodeEnum.ORGANISATION_NOT_FOUND.getTemplate(),id.toString()));
        if (organisationRepository.existsByDomain(organisationRequest.getDomain())) {
            throw new ServiceRuntimeException(ErrorCodeEnum.ORGANISATION_DOMAIN_EXISTS,ErrorCodeEnum.ORGANISATION_DOMAIN_EXISTS.getTemplate(), organisationRequest.getDomain());
        }
        organisation.setName(organisationRequest.getName());
        organisation.setDomain(organisationRequest.getDomain());
        return organisationRepository.save(organisation);
    }
}
