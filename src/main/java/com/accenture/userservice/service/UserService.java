package com.accenture.userservice.service;

import com.accenture.userservice.dto.EmailVerificationDto;
import com.accenture.userservice.exception.ResourceNotFoundException;
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
    String domain = getDomainNameFromEmail(user.getEmail());
    
    if(userRepository.existsUserByEmail(user.getEmail())) {
      throw new Exception("User with " + user.getEmail() + " is already exist");
    }
    if(organisationRepository.findByDomain(domain) == null) {
      throw new Exception("Email domain is invalid!!");
    }
    user.setOrganisation(organisationRepository.findByDomain(domain).orElseThrow(() -> new ResourceNotFoundException("Organisation not found !")));
    user.setStatus(Status.INACTIVE);
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
  
  public User updateUserDetails(User userRes, Long id) {
    return userRepository.findById(id).map(user -> {
      user.setName(userRes.getName());
      user.setEmail(userRes.getEmail());
      return userRepository.save(user);
    }).orElseThrow(() -> new ResourceNotFoundException("User Not Found For for ID :: " + id));
  }
  
  private String getDomainNameFromEmail(String emailId) {
    return StringUtils.substringAfter(emailId, "@");
  }
  
  public EmailVerificationDto emailVerificationToken(String email, Long requestToken) throws Exception {
    User user = userRepository.findByEmail(email);
    return emailVerificationService.checkEmailVerification(user.getId(), requestToken);
  }
  
}
