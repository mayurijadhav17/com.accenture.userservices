package com.accenture.userservice.service;

import com.accenture.userservice.exception.ResourceNotFoundException;
import com.accenture.userservice.model.EmailVerificationDetails;
import com.accenture.userservice.model.Organisation;
import com.accenture.userservice.model.User;
import com.accenture.userservice.repo.EmailVerificationDetailsRepository;
import com.accenture.userservice.repo.OrganisationRepository;
import com.accenture.userservice.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j

public class UserService {
private UserRepository userRepository;
private OrganisationRepository organisationRepository;
private EmailVerificationDetailsRepository emailVerificationDetailsRepository;
private static final int MAX_ATTEMPTS = 3;

@Autowired
public UserService(UserRepository userRepository, OrganisationRepository organisationRepository, EmailVerificationDetailsRepository emailVerificationDetailsRepository) {
	this.userRepository = userRepository;
	this.organisationRepository = organisationRepository;
	this.emailVerificationDetailsRepository = emailVerificationDetailsRepository;
}

@Transactional
public User createUser(User user) throws Exception {
	String domain = getDomainNameFromEmail(user.getEmail());
	log.info("email Domain--->" + domain);
	if (userRepository.existsUserByEmail(user.getEmail())) {
		throw new Exception("User with " + user.getEmail() + " is already exist");
	}
	if (organisationRepository.findByDomain(domain) == null) {
		throw new Exception("Email domain is invalid!!");
	}
	user = userRepository.save(user);
	log.info("user Saved !!");
	updateEmailVerificationDetails(user);
	update_User_Orgnisation_FK(user, domain);
	return user;
}

private User update_User_Orgnisation_FK(User user, String domain)	{
	Organisation organisation = organisationRepository.findByDomain(domain);
	user.setOrganisation(organisation);
	return updateUserDetails(user, user.getId());
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
		user.setPhoneNumber(userRes.getPhoneNumber());
		return userRepository.save(user);
	}).orElseThrow(() -> new ResourceNotFoundException("User Not Found For for ID :: " + id));
}

private void updateEmailVerificationDetails(User user) throws Exception {
	String authCode = UUID.randomUUID().toString();
	Integer totalAttempts = 0;
	EmailVerificationDetails emailVerificationDetails = new EmailVerificationDetails();
	emailVerificationDetails.setUser(user);
	emailVerificationDetails.setEmail(user.getEmail());
	emailVerificationDetails.setCode(authCode);
	emailVerificationDetails.setTotal_attempts(0);
	emailVerificationDetailsRepository.save(emailVerificationDetails);
	log.info("Email confirmation Code-------------->" + authCode);
}

private String getDomainNameFromEmail(String emailId) {
	String domain = "";
	domain = StringUtils.substringAfter(emailId, "@");
	return domain;
}

public String emailConfirmation(String email, String Inputcode) throws Exception {
	EmailVerificationDetails emailVerificationDetails = emailVerificationDetailsRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("email  details invalid = " + email));
	Integer total_attempts = emailVerificationDetails.getTotal_attempts();
	String code = emailVerificationDetails.getCode();

	// Saving total_attempts session to table
	emailVerificationDetails.setTotal_attempts(total_attempts + 1);
	emailVerificationDetailsRepository.save(emailVerificationDetails);

	if (total_attempts +1>= MAX_ATTEMPTS) {
		deleteUserById(emailVerificationDetails.getUser().getId());
		log.info("user record deleted ");
		throw new Exception("Email Verification 3 attempts Over ,User record deleted!!");
	}
	if (!code.equals(Inputcode)) {
		throw new Exception("Verification code is not matching !!");
	}
	return "Email Id verified!!";
}
}
