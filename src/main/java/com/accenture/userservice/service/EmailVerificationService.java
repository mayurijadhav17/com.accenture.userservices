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

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailVerificationService {
  
  private final EmailVerificationRepository emailVerificationRepository;
  private final UserRepository userRepository;
  private final UserRegistrationProperties userRegistrationProperties;
  
  private Date calculateExpiryDate(int expiryTimeInMinutes) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Timestamp(cal.getTime().getTime()));
    cal.add(Calendar.MINUTE, expiryTimeInMinutes);
    return new Date(cal.getTime().getTime());
  }
  
  private Date currentDateTIme() {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Timestamp(cal.getTime().getTime()));
    return new Date(cal.getTime().getTime());
  }
  
  public void saveEmailVerification(User user) throws Exception {
    // 6 digit token generation
    String authCode = UUID.randomUUID().toString();
    Long token = Long.valueOf(new Random().nextInt(1000000));
    EmailVerification emailVerification = new EmailVerification();
    emailVerification.setUserId(user.getId());
    emailVerification.setToken(token);
    emailVerification.setExpiryDate(calculateExpiryDate(userRegistrationProperties.getExpiration()));
    emailVerification.setTotalAttempts(0);
    emailVerificationRepository.save(emailVerification);
    log.info("Email confirmation Code-------------->" + authCode);
  }
  
  public EmailVerificationDto checkEmailVerification(Long userId, Long requestToken) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not found !"));
    EmailVerification emailVerification = emailVerificationRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("User Not found !"));
    Integer totalAttempts = emailVerification.getTotalAttempts();
    Long token = emailVerification.getToken();
    EmailVerificationDto emailVerificationDto = new EmailVerificationDto();
    // Saving total_attempts session to table
    emailVerification.setTotalAttempts(totalAttempts + 1);
    emailVerificationRepository.save(emailVerification);
    
    if(totalAttempts + 1 <= userRegistrationProperties.getMaxAttempts() && token.equals(requestToken) && (currentDateTIme()).compareTo((emailVerification.getExpiryDate())) < 0) {
      user.setStatus(Status.ACTIVE);
      userRepository.save(user);
      emailVerificationDto.setResponseMessage("Email verified !!");
    }
    if(!token.equals(requestToken) && totalAttempts + 1 <= userRegistrationProperties.getMaxAttempts()) {
      emailVerificationDto.setResponseMessage("Email token is not matching !!");
    }
    if(totalAttempts + 1 >= userRegistrationProperties.getMaxAttempts() || (currentDateTIme()).compareTo((emailVerification.getExpiryDate())) > 0) {
      //deleting user
      userRepository.deleteById(userId);
      emailVerificationDto.setResponseMessage("Email verification code expired or attempts over 3 for you !! User record deleted");
    }
    
    return emailVerificationDto;
  }
}
