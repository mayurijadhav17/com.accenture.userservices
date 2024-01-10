package com.accenture.userService.service;


import com.accenture.userservice.model.Organisation;
import com.accenture.userservice.repo.OrganisationRepository;
import com.accenture.userservice.service.OrganisationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrganisationServiceTest{
  @Mock
  private OrganisationRepository organisationRepository;
  private OrganisationService service;

  @BeforeEach
  void setUp() {
    service = new OrganisationService(organisationRepository);
  }

  @Test
  void testCreateOrganisation() throws Exception {
    // Setup
    Organisation organisation = Organisation.builder().build();
    Organisation expectedResult = Organisation.builder().build();
    //test
    when(service.create(organisation)).thenReturn(expectedResult);
    Organisation result = service.create(organisation);
    assertEquals(expectedResult, result);
  }

  @Test
  void testGetOrganisationById() throws Exception {
    Organisation organisation = Organisation.builder().build();
    //test
    when(organisationRepository.findById(anyLong())).thenReturn(Optional.of(organisation));
    Organisation result = service.getOrganisationById(anyLong());
    assertEquals(organisation, result);

  }

  @Test
  void testGetOrganisationList() throws Exception {
    // Setup
    Organisation organisation1 = Organisation.builder().build();
    Organisation organisation2 = Organisation.builder().build();
    List<Organisation> list = new ArrayList<>();
    list.add(organisation1);
    list.add(organisation2);
    when(service.getAllOrganisations()).thenReturn(list);
    List<Organisation> result = service.getAllOrganisations();
    assertEquals(list, result);

  }
  @Test
  void testUpdateOrganisationDetails() throws Exception {
    // Setup
    final Organisation organisationRequest = Organisation.builder()
            .name("name")
            .domain("domain")
            .build();
    final Organisation expectedResult = Organisation.builder()
            .name("name")
            .domain("domain")
            .build();

    // Configure OrganisationRepository.findById(...).
    final Optional<Organisation> organisation = Optional.of(Organisation.builder()
            .name("name")
            .domain("domain")
            .build());
    when(organisationRepository.findById(0L)).thenReturn(organisation);
    when(organisationRepository.existsByDomain("domain")).thenReturn(false);
    final Organisation organisation1 = Organisation.builder()
            .name("name")
            .domain("domain")
            .build();
    when(organisationRepository.save(Organisation.builder()
            .name("name")
            .domain("domain")
            .build())).thenReturn(organisation1);
    final Organisation result = service.updateOrganisationDetails(organisationRequest, 0L);
    assertEquals(result,expectedResult);
  }

}