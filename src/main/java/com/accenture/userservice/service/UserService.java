package com.accenture.userservice.service;

import com.accenture.userservice.dto.UserDTO;
import com.accenture.userservice.exception.ResourceNotFoundException;
import com.accenture.userservice.model.User;
import com.accenture.userservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	
	private UserRepository userRepository;
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUserById(int id) {
		return	userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not found with id = " + id));
	}
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	public void deleteUserById(int id) {
		if (!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("User Not Found for id "+ id );
		}
		userRepository.deleteById(id);
	}
	public User UpdateUserDetails(User userRes, int id) {
		return userRepository.findById(id).map(user -> {
			user.setName(userRes.getName());
			user.setAddress(userRes.getAddress());
			user.setEmail(userRes.getEmail());
			user.setPhonenumber(userRes.getPhonenumber());
			return userRepository.save(user);
		}).orElseThrow(() -> new ResourceNotFoundException("User Not Found For for ID :: " + id));
	}
	
}
