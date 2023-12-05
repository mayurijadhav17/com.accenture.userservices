package com.accenture.userservice.controller;

import com.accenture.userservice.model.User;
import com.accenture.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
private final UserService userService;

@Autowired
public UserController(UserService userService) {
	this.userService = userService;
}

@PostMapping
public User addUser(@RequestBody User user) throws Exception {
	return userService.createUser(user);
}

@GetMapping("/{id}")
public User getUserById(@PathVariable Long id) {
	return userService.getUserById(id);
}

@GetMapping
public List<User> getAllUsers() {
	return userService.getUsers();
}

@PutMapping("{id}")
public User updateUser(@RequestBody User user, @PathVariable Long id) {
	return userService.updateUserDetails(user, id);
}

@DeleteMapping("{id}")
public void deleteUser(@PathVariable Long id) {
	userService.deleteUserById(id);
}

@PostMapping("/emailConfirmation/{email}/{code}")
public String emailConfirmation(@PathVariable String email,@PathVariable String code) {
return userService.emailConfirmation(email,code);
}
@GetMapping ("/getEmailCode")
public String getEmailCode() {
	return userService.getEmailCode();
}
}