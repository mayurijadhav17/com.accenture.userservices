package com.accenture.userservice.service;

import com.accenture.userservice.configuration.UserRegistrationProperties;
import com.accenture.userservice.dto.EmailVerificationDto;
import com.accenture.userservice.exception.ResourceNotFoundException;
import com.accenture.userservice.model.EmailVerification;
import com.accenture.userservice.model.Status;
import com.accenture.userservice.model.User;
import com.accenture.userservice.repo.EmailVerificationRepository;
import com.accenture.userservice.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailVerificationService {
  
  private final EmailVerificationRepository emailVerificationRepository;
  private final UserRepository userRepository;
  private final UserRegistrationProperties userRegistrationProperties;
  
  //calculate expiry date time
  private LocalDateTime calculateExpiryDate(int expiryTimeInMinutes) {
    return currentDateTIme().plusMinutes(userRegistrationProperties.getExpiration());
  }
  
  // get the current date time
  private LocalDateTime currentDateTIme() {
    return LocalDateTime.now();
  }
  
  public void saveEmailVerification(User user) throws Exception {
    // 6 digit token generation
    Long token = Long.valueOf(new Random().nextInt(100000, 999999));
    EmailVerification emailVerification = new EmailVerification();
    emailVerification.setUser(user);
    emailVerification.setToken(token);
    emailVerification.setExpiryDate(calculateExpiryDate(userRegistrationProperties.getExpiration()));
    emailVerification.setTotalAttempts(0);
    emailVerificationRepository.save(emailVerification);
  }
  
  public EmailVerificationDto checkEmailVerification(Long userId, Long requestToken) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not found !"));
    EmailVerification emailVerification = emailVerificationRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not found !"));
    Integer totalAttempts = emailVerification.getTotalAttempts();
    Long token = emailVerification.getToken();
    EmailVerificationDto emailVerificationDto = new EmailVerificationDto();
    // Saving total_attempts session to table
    emailVerification.setTotalAttempts(totalAttempts + 1);
    emailVerificationRepository.save(emailVerification);
    
    if(totalAttempts + 1 <= userRegistrationProperties.getMaxAttempts()) {
      if(token.equals(requestToken) && (currentDateTIme()).compareTo((emailVerification.getExpiryDate())) < 0) {
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        emailVerificationDto.setResponseMessage("Email verified !!");
      }
      if(!token.equals(requestToken)) {
        emailVerificationDto.setResponseMessage("Email token is not matching !!");
      }
      if(((currentDateTIme()).compareTo((emailVerification.getExpiryDate())) > 0)) {
        emailVerificationDto.setResponseMessage("Email verification code expired");
      }
    } else {
      //deleting user
      userRepository.deleteById(userId);
      emailVerificationDto.setResponseMessage("total attempts over 3 for you !! User record deleted");
    }
    return emailVerificationDto;
  }
}
