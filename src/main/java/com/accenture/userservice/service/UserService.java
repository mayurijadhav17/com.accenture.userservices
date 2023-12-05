package com.accenture.userservice.service;

import com.accenture.userservice.exception.ResourceNotFoundException;
import com.accenture.userservice.model.User;
import com.accenture.userservice.repo.OrganisationRepository;
import com.accenture.userservice.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserService {
private UserRepository userRepository;
private OrganisationRepository organisationRepository;
private String authToken;
private static final int MAX_ATTEMPTS = 3;
private  int Total_Attemps = 0;

@Autowired
public UserService(UserRepository userRepository, OrganisationRepository organisationRepository) {
	this.userRepository = userRepository;
	this.organisationRepository = organisationRepository;
}

public User createUser(User user) throws Exception {
	if (userRepository.existsUserByEmail(user.getEmail())) {
		throw new Exception("User with " + user.getEmail() + " is already exist");
	} else if (!organisationRepository.existsById(user.getOrgnisation_id())) {
		throw new Exception("Organisation id invalid!!");
	} else {
		log.info("user created");
		return userRepository.save(user);
	}
}

public User getUserById(Long id) {
	return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not found with id = " + id));
}

public List<User> getUsers() {
	return userRepository.findAll();
}

public void deleteUserById(Long id) {
	if (!userRepository.existsById(id)) {
		throw new ResourceNotFoundException("User Not Found for id " + id);
	}
	userRepository.deleteById(id);
}

public User updateUserDetails(User userRes, Long id) {
	return userRepository.findById(id).map(user -> {
		user.setName(userRes.getName());
		user.setAddress(userRes.getAddress());
		user.setEmail(userRes.getEmail());
		user.setPhonenumber(userRes.getPhonenumber());
		return userRepository.save(user);
	}).orElseThrow(() -> new ResourceNotFoundException("User Not Found For for ID :: " + id));
}

public String getEmailCode() {
	authToken = UUID.randomUUID().toString();
	log.info("Code-------------->" + authToken);
	return "Code is generated";
}

public String emailConfirmation(String email, String code) {
	log.info("user email-->" + email);
	log.info("tokens-->" + authToken + " &&  " + code);
	String responseMsg = "";
	log.info("total attempts --" + Total_Attemps + 1);
	if (authToken.equals(code)) {
		responseMsg = "Email is  confirmed !!";
	} else {
		//	userRepository.deleteByEmail(email);
		responseMsg = "Email confirmation failed & user record deleted";
	}
	return responseMsg;
}
}
