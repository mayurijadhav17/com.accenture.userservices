package com.accenture.userservice.service;

import com.accenture.userservice.dto.EmailVerificationDto;
import com.accenture.userservice.exception.EMailAlreadyExistException;
import com.accenture.userservice.exception.ResourceNotFoundException;
import com.accenture.userservice.model.Organisation;
import com.accenture.userservice.model.UserStatusEnum;
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
      throw new EMailAlreadyExistException("EMAIL_ALREADY_EXIST" + user.getEmail());
    }
    user.setStatus(UserStatusEnum.INACTIVE);
    String domain = getDomain(user.getEmail());
    if(!organisationRepository.existsByDomain(domain)) {
      throw new Exception("EMAIL_INVALID" + domain);
    }
    Organisation organisation = organisationRepository.findByDomain(domain)
            .orElseThrow(() -> new ResourceNotFoundException("ORGANISATION_NOT_FOUND" + domain));
    user.setOrganisation(organisation);
    //saving email verification data
    userRepository.save(user);
    emailVerificationService.sendEmailVerificationCode(user);
    return user;
  }
  
  public User getUserById(Long id) {
    return userRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("USER_NOT_FOUND" + id));
  }
  
  public List<User> getUsers() {
    return userRepository.findAll();
  }
  
  public void deleteUserById(Long id) {
    if(!userRepository.existsById(id)) {
      throw new ResourceNotFoundException("USER_NOT_FOUND" + id);
    }
    userRepository.deleteById(id);
  }
  
  public User updateUserDetails(User userRes, Long id) throws Exception {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("USER_NOT_FOUND" + id));
    if(userRepository.existsUserByEmail(userRes.getEmail())) {
      throw new EMailAlreadyExistException("EMAIL_ALREADY_EXIST" + user.getEmail());
    }
    user.setName(userRes.getName());
    user.setEmail(user.getEmail());
    String domain = getDomain(userRes.getEmail());
    if(!organisationRepository.existsByDomain(domain)) {
      throw new Exception("EMAIL_INVALID" + domain);
    }
    Organisation organisation = organisationRepository.findByDomain(domain)
            .orElseThrow(() -> new ResourceNotFoundException("ORGANISATION_NOT_FOUND" + domain));
    user.setOrganisation(organisation);
    return userRepository.save(user);
  }
  
  public EmailVerificationDto emailVerificationToken(String email, int requestToken) throws Exception {
    User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("USER_NOT_FOUND"));;
    return emailVerificationService.checkEmailVerification(user.getId(), requestToken);
  }
  
  private String getDomain(String email) {
    return StringUtils.substringAfter(email, "@");
  }
}
