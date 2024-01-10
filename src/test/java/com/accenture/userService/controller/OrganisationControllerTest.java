package com.accenture.userService.controller;

import com.accenture.userservice.controller.OrganisationController;
import com.accenture.userservice.model.Organisation;
import com.accenture.userservice.service.OrganisationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrganisationControllerTest {
  @Mock
  private OrganisationService service;
  private OrganisationController organisationController;

  @BeforeEach
  void setUp() {
    organisationController = new OrganisationController(service);
  }

  @Test
  void testCreateOrganisation() throws Exception {
    // Setup
    Organisation organisation = Organisation.builder().build();
    Organisation expectedResult = Organisation.builder().build();
    //test
    when(organisationController.addOrganisation(organisation)).thenReturn(expectedResult);
    Organisation result = organisationController.addOrganisation(organisation);
    assertEquals(expectedResult, result);
  }

  @Test
  void testGetOrganisationById() throws Exception {
    // Setup
    Organisation expectedResult = new Organisation();
    expectedResult.setDomain("test.com");
    expectedResult.setId(1L);
    expectedResult.setName("TestOrganisation");
    //test
    when(organisationController.getOrganisationById(1L)).thenReturn(expectedResult);
    Organisation result = organisationController.getOrganisationById(1L);
    assertEquals(expectedResult, result);

  }

  @Test
  void testGetOrganisationList() throws Exception {
    // Setup
    Organisation organisation1 = Organisation.builder().build();
    Organisation organisation2 = Organisation.builder().build();
    List<Organisation> list = new ArrayList<>();
    list.add(organisation1);
    list.add(organisation2);
    when(organisationController.getAllOrganisation()).thenReturn(list);
    List<Organisation> result = organisationController.getAllOrganisation();
    assertEquals(list, result);

  }

  @Test
  void testUpdateOrganisation() throws Exception {
    // Setup
    Organisation expectedResult = new Organisation();
    expectedResult.setDomain("test.com");
    expectedResult.setId(1L);
    expectedResult.setName("TestOrganisation");
    Organisation organisation = Organisation.builder().build();
    //test
    when(organisationController.updateOrganisation(organisation, organisation.getId())).thenReturn(expectedResult);
    Organisation result = organisationController.updateOrganisation(organisation, organisation.getId());
    assertEquals(expectedResult, result);
  }
}