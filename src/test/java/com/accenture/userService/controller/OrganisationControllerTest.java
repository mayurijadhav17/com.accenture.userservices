//package com.accenture.userService.controller;
//
//import com.accenture.userservice.controller.OrganisationController;
//import com.accenture.userservice.model.Organisation;
//import com.accenture.userservice.model.User;
//import com.accenture.userservice.service.OrganisationService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(OrganisationController.class)
//class OrganisationControllerTest {
//
//  @Autowired
//  private MockMvc mockMvc;
//
//  @MockBean
//  private OrganisationService mockOrganisationService;
//
//  @Test
//  void testAddOrganisation() throws Exception {
//    // Setup
//    // Configure OrganisationService.create(...).
//    final Organisation organisation = new Organisation();
//    organisation.setId(0L);
//    organisation.setName("name");
//    organisation.setDomain("domain");
//    final User user = new User();
//    user.setId(0L);
//    organisation.setUsersList(List.of(user));
//    final Organisation organisation1 = new Organisation();
//    organisation1.setId(0L);
//    organisation1.setName("name");
//    organisation1.setDomain("domain");
//    final User user1 = new User();
//    user1.setId(0L);
//    organisation1.setUsersList(List.of(user1));
//    when(mockOrganisationService.create(organisation1)).thenReturn(organisation);
//
//    // Run the test
//    final MockHttpServletResponse response = mockMvc.perform(post("/api/organisation")
//                    .content("content").contentType(MediaType.APPLICATION_JSON)
//                    .accept(MediaType.APPLICATION_JSON))
//            .andReturn().getResponse();
//
//    // Verify the results
//    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//  }
//
//  @Test
//  void testAddOrganisation_OrganisationServiceThrowsException() throws Exception {
//    // Setup
//    // Configure OrganisationService.create(...).
//    final Organisation organisation = new Organisation();
//    organisation.setId(0L);
//    organisation.setName("name");
//    organisation.setDomain("domain");
//    final User user = new User();
//    user.setId(0L);
//    organisation.setUsersList(List.of(user));
//    when(mockOrganisationService.create(organisation)).thenThrow(Exception.class);
//
//    // Run the test
//    final MockHttpServletResponse response = mockMvc.perform(post("/api/organisation")
//                    .content("content").contentType(MediaType.APPLICATION_JSON)
//                    .accept(MediaType.APPLICATION_JSON))
//            .andReturn().getResponse();
//
//    // Verify the results
//    assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
//    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//  }
//
//  @Test
//  void testGetOrganisationById() throws Exception {
//    // Setup
//    // Configure OrganisationService.getOrganisationById(...).
//    final Organisation organisation = new Organisation();
//    organisation.setId(0L);
//    organisation.setName("name");
//    organisation.setDomain("domain");
//    final User user = new User();
//    user.setId(0L);
//    organisation.setUsersList(List.of(user));
//    when(mockOrganisationService.getOrganisationById(0L)).thenReturn(organisation);
//
//    // Run the test
//    final MockHttpServletResponse response = mockMvc.perform(get("/api/organisation/{id}", 0)
//                    .accept(MediaType.APPLICATION_JSON))
//            .andReturn().getResponse();
//
//    // Verify the results
//    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//  }
//
//  @Test
//  void testGetAllOrganisation() throws Exception {
//    // Setup
//    // Configure OrganisationService.getAllOrganisations(...).
//    final Organisation organisation = new Organisation();
//    organisation.setId(0L);
//    organisation.setName("name");
//    organisation.setDomain("domain");
//    final User user = new User();
//    user.setId(0L);
//    organisation.setUsersList(List.of(user));
//    final List<Organisation> organisations = List.of(organisation);
//    when(mockOrganisationService.getAllOrganisations()).thenReturn(organisations);
//
//    // Run the test
//    final MockHttpServletResponse response = mockMvc.perform(get("/api/organisation")
//                    .accept(MediaType.APPLICATION_JSON))
//            .andReturn().getResponse();
//
//    // Verify the results
//    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//  }
//
//  @Test
//  void testGetAllOrganisation_OrganisationServiceReturnsNoItems() throws Exception {
//    // Setup
//    when(mockOrganisationService.getAllOrganisations()).thenReturn(Collections.emptyList());
//
//    // Run the test
//    final MockHttpServletResponse response = mockMvc.perform(get("/api/organisation")
//                    .accept(MediaType.APPLICATION_JSON))
//            .andReturn().getResponse();
//
//    // Verify the results
//    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//    assertThat(response.getContentAsString()).isEqualTo("[]");
//  }
//
//  @Test
//  void testDeleteOrganisation() throws Exception {
//    // Setup
//    // Run the test
//    final MockHttpServletResponse response = mockMvc.perform(delete("/api/organisation/{id}", 0)
//                    .accept(MediaType.APPLICATION_JSON))
//            .andReturn().getResponse();
//
//    // Verify the results
//    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    verify(mockOrganisationService).deleteById(0L);
//  }
//}
