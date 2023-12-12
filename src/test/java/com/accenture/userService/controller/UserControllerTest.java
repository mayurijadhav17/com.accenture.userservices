//package com.accenture.userService.controller;
//
//import com.accenture.userservice.controller.UserController;
//import com.accenture.userservice.dto.EmailVerificationDto;
//import com.accenture.userservice.model.Organisation;
//import com.accenture.userservice.model.Status;
//import com.accenture.userservice.model.User;
//import com.accenture.userservice.service.UserService;
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
//@WebMvcTest(UserController.class)
//class UserControllerTest {
//
//  @Autowired
//  private MockMvc mockMvc;
//
//  @MockBean
//  private UserService mockUserService;
//
//  @Test
//  void testAddUser() throws Exception {
//    // Setup
//    // Configure UserService.createUser(...).
//    final User user = new User();
//    user.setId(0L);
//    user.setName("name");
//    user.setEmail("email");
//    user.setStatus(Status.ACTIVE);
//    final Organisation organisation = new Organisation();
//    user.setOrganisation(organisation);
//    final User user1 = new User();
//    user1.setId(0L);
//    user1.setName("name");
//    user1.setEmail("email");
//    user1.setStatus(Status.ACTIVE);
//    final Organisation organisation1 = new Organisation();
//    user1.setOrganisation(organisation1);
//    when(mockUserService.createUser(user1)).thenReturn(user);
//
//    // Run the test
//    final MockHttpServletResponse response = mockMvc.perform(post("/api/user")
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
//  void testAddUser_UserServiceThrowsException() throws Exception {
//    // Setup
//    // Configure UserService.createUser(...).
//    final User user = new User();
//    user.setId(0L);
//    user.setName("name");
//    user.setEmail("email");
//    user.setStatus(Status.ACTIVE);
//    final Organisation organisation = new Organisation();
//    user.setOrganisation(organisation);
//    when(mockUserService.createUser(user)).thenThrow(Exception.class);
//
//    // Run the test
//    final MockHttpServletResponse response = mockMvc.perform(post("/api/user")
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
//  void testGetUserById() throws Exception {
//    // Setup
//    // Configure UserService.getUserById(...).
//    final User user = new User();
//    user.setId(0L);
//    user.setName("name");
//    user.setEmail("email");
//    user.setStatus(Status.ACTIVE);
//    final Organisation organisation = new Organisation();
//    user.setOrganisation(organisation);
//    when(mockUserService.getUserById(0L)).thenReturn(user);
//
//    // Run the test
//    final MockHttpServletResponse response = mockMvc.perform(get("/api/user/{id}", 0)
//                    .accept(MediaType.APPLICATION_JSON))
//            .andReturn().getResponse();
//
//    // Verify the results
//    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//  }
//
//  @Test
//  void testGetAllUsers() throws Exception {
//    // Setup
//    // Configure UserService.getUsers(...).
//    final User user = new User();
//    user.setId(0L);
//    user.setName("name");
//    user.setEmail("email");
//    user.setStatus(Status.ACTIVE);
//    final Organisation organisation = new Organisation();
//    user.setOrganisation(organisation);
//    final List<User> users = List.of(user);
//    when(mockUserService.getUsers()).thenReturn(users);
//
//    // Run the test
//    final MockHttpServletResponse response = mockMvc.perform(get("/api/user")
//                    .accept(MediaType.APPLICATION_JSON))
//            .andReturn().getResponse();
//
//    // Verify the results
//    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//  }
//
//  @Test
//  void testGetAllUsers_UserServiceReturnsNoItems() throws Exception {
//    // Setup
//    when(mockUserService.getUsers()).thenReturn(Collections.emptyList());
//
//    // Run the test
//    final MockHttpServletResponse response = mockMvc.perform(get("/api/user")
//                    .accept(MediaType.APPLICATION_JSON))
//            .andReturn().getResponse();
//
//    // Verify the results
//    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//    assertThat(response.getContentAsString()).isEqualTo("[]");
//  }
//
//  @Test
//  void testUpdateUser() throws Exception {
//    // Setup
//    // Configure UserService.updateUserDetails(...).
//    final User user = new User();
//    user.setId(0L);
//    user.setName("name");
//    user.setEmail("email");
//    user.setStatus(Status.ACTIVE);
//    final Organisation organisation = new Organisation();
//    user.setOrganisation(organisation);
//    final User userRes = new User();
//    userRes.setId(0L);
//    userRes.setName("name");
//    userRes.setEmail("email");
//    userRes.setStatus(Status.ACTIVE);
//    final Organisation organisation1 = new Organisation();
//    userRes.setOrganisation(organisation1);
//    when(mockUserService.updateUserDetails(userRes, 0L)).thenReturn(user);
//
//    // Run the test
//    final MockHttpServletResponse response = mockMvc.perform(put("/api/user/{id}", 0)
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
//  void testUpdateUser_UserServiceThrowsException() throws Exception {
//    // Setup
//    // Configure UserService.updateUserDetails(...).
//    final User userRes = new User();
//    userRes.setId(0L);
//    userRes.setName("name");
//    userRes.setEmail("email");
//    userRes.setStatus(Status.ACTIVE);
//    final Organisation organisation = new Organisation();
//    userRes.setOrganisation(organisation);
//    when(mockUserService.updateUserDetails(userRes, 0L)).thenThrow(Exception.class);
//
//    // Run the test
//    final MockHttpServletResponse response = mockMvc.perform(put("/api/user/{id}", 0)
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
//  void testDeleteUser() throws Exception {
//    // Setup
//    // Run the test
//    final MockHttpServletResponse response = mockMvc.perform(delete("/api/user/{id}", 0)
//                    .accept(MediaType.APPLICATION_JSON))
//            .andReturn().getResponse();
//
//    // Verify the results
//    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    verify(mockUserService).deleteUserById(0L);
//  }
//
//  @Test
//  void testEmailVerificationToken() throws Exception {
//    // Setup
//    // Configure UserService.emailVerificationToken(...).
//    final EmailVerificationDto emailVerificationDto = new EmailVerificationDto();
//    emailVerificationDto.setResponseMessage("responseMessage");
//    when(mockUserService.emailVerificationToken("email", 0L)).thenReturn(emailVerificationDto);
//
//    // Run the test
//    final MockHttpServletResponse response = mockMvc.perform(
//                    post("/api/user/emailVerification/{email}/{token}", "email", 0)
//                            .accept(MediaType.APPLICATION_JSON))
//            .andReturn().getResponse();
//
//    // Verify the results
//    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//  }
//
//  @Test
//  void testEmailVerificationToken_UserServiceThrowsException() throws Exception {
//    // Setup
//    when(mockUserService.emailVerificationToken("email", 0L)).thenThrow(Exception.class);
//
//    // Run the test
//    final MockHttpServletResponse response = mockMvc.perform(
//                    post("/api/user/emailVerification/{email}/{token}", "email", 0)
//                            .accept(MediaType.APPLICATION_JSON))
//            .andReturn().getResponse();
//
//    // Verify the results
//    assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
//    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//  }
//}
