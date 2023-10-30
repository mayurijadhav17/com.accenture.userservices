package com.accenture.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.accenture.userservice.model.User;
import com.accenture.userservice.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private final UserService userService;
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping()
	public User addUser(@RequestBody User user) {
		
		
		return userService.createUser(user);
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable int id) {
		
		
		
		return userService.getUserById(id);
	}

	@GetMapping()
	public List<User> getAllUsers() {
		return userService.getUsers();
	}	

	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable int id) {
		 userService.deleteUserById(id);
	}
}
