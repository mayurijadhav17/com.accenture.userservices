package com.accenture.userService.controller;

import com.accenture.userservice.controller.UserController;
import com.accenture.userservice.model.User;
import com.accenture.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
  
  @Mock
  private UserService service;
  private UserController userController;
  
  @BeforeEach
  void setUp() {
    userController = new UserController(service);
  }
  
  @Test
  void testAddUser() throws Exception {
    // Setup
    User user = User.builder().build();
    User expectedResult = User.builder().build();
    //test
    when(userController.addUser(user)).thenReturn(expectedResult);
    User result = userController.addUser(user);
    assertEquals(expectedResult, result);
  }
  
  @Test
  void testGetUserById() throws Exception {
    // Setup
    User expectedResult = User.builder().build();
    //test
    when(userController.getUserById(1L)).thenReturn(expectedResult);
    User result = userController.getUserById(1L);
    assertEquals(expectedResult, result);
    
  }
  
  @Test
  void testGetOrganisationList() throws Exception {
    // Setup
    User user1 = User.builder().build();
    User user2 = User.builder().build();
    List<User> list = new ArrayList<>();
    list.add(user1);
    list.add(user2);
    when(userController.getAllUsers()).thenReturn(list);
    List<User> result = userController.getAllUsers();
    assertEquals(list, result);
    
  }
  
  @Test
  void testUpdateUser() throws Exception {
    // Setup
    User expectedResult = User.builder().build();
    User user = User.builder().build();
    //test
    when(userController.updateUser(user, user.getId())).thenReturn(expectedResult);
    User result = userController.updateUser(user, user.getId());
    assertEquals(expectedResult, result);
  }
}
