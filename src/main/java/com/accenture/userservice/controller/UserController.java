package com.accenture.userservice.controller;

import com.accenture.userservice.dto.EmailVerificationDto;
import com.accenture.userservice.model.User;
import com.accenture.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
  private final UserService userService;
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public User addUser(@RequestBody @Valid User user) throws Exception {
    return userService.createUser(user);
  }
  @PreAuthorize("hasRole('USER')")
  @GetMapping("/{id}")
  public User getUserById(@PathVariable Long id) {
    return userService.getUserById(id);
  }
  @PreAuthorize("hasRole('USER')")
  @GetMapping
  public List<User> getAllUsers() {
    return userService.getUsers();
  }
  
  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("{id}")
  public User updateUser(@RequestBody @Valid User user, @PathVariable Long id) throws Exception {
    return userService.updateUserDetails(user, id);
  }
  
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUserById(id);
  }
  
  @PreAuthorize("hasRole('USER')")
  @PostMapping("/emailVerification/{email}/{token}")
  public EmailVerificationDto emailVerificationToken(@PathVariable String email, @PathVariable Integer token) throws Exception {
    return userService.emailVerificationToken(email, token);
  }
  
}