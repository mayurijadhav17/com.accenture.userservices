package com.accenture.userservice.service;

import com.accenture.userservice.dto.EmailVerificationDto;
import com.accenture.userservice.exception.ResourceNotFoundException;
import com.accenture.userservice.model.Organisation;
import com.accenture.userservice.model.Status;
import com.accenture.userservice.model.User;
import com.accenture.userservice.repo.OrganisationRepository;
import com.accenture.userservice.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
  
  private final UserRepository userRepository;
  private final OrganisationRepository organisationRepository;
  private final EmailVerificationService emailVerificationService;
  
  @Transactional
  public User createUser(User user) throws Exception {
    
    if(userRepository.existsUserByEmail(user.getEmail())) {
      throw new Exception("User with " + user.getEmail() + " is already exist");
    }
    user.setStatus(Status.INACTIVE);
    user.setOrganisation(checkOrganisationDetails(user.getEmail()));
    //saving email verification data
    userRepository.save(user);
    emailVerificationService.saveEmailVerification(user);
    return user;
  }
  
  public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not found with id = " + id));
  }
  
  public List<User> getUsers() {
    return userRepository.findAll();
  }
  
  public void deleteUserById(Long id) {
    if(!userRepository.existsById(id)) {
      throw new ResourceNotFoundException("User Not Found for id " + id);
    }
    userRepository.deleteById(id);
  }
  
  public User updateUserDetails(User userRes, Long id) throws Exception {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("user not found for id!!" + id));
    if(userRepository.existsUserByEmail(userRes.getEmail())) {
      throw new Exception("User with " + user.getEmail() + " is already exist");
    }
    user.setName(userRes.getName());
    user.setEmail(user.getEmail());
    user.setOrganisation(checkOrganisationDetails(userRes.getEmail()));
    return userRepository.save(user);
  }
  
  private Organisation checkOrganisationDetails(String email) throws Exception {
    //get domain name from email id
    String domain = StringUtils.substringAfter(email, "@");
    if(!organisationRepository.existsByDomain(domain)) {
      throw new Exception("Email domain is invalid!!");
    }
    Organisation organisation = organisationRepository.findByDomain(domain)
            .orElseThrow(() -> new ResourceNotFoundException("Organisation not found !"));
    return organisation;
  }
  
  public EmailVerificationDto emailVerificationToken(String email, Long requestToken) throws Exception {
    User user = userRepository.findByEmail(email);
    return emailVerificationService.checkEmailVerification(user.getId(), requestToken);
  }
  
}
