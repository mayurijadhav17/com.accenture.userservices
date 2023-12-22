package com.accenture.userservice.service;

import com.accenture.userservice.configuration.UserRegistrationProperties;
import com.accenture.userservice.dto.EmailVerificationDto;
import com.accenture.userservice.exception.ServiceRuntimeException;
import com.accenture.userservice.feignClient.EmailFeignClient;
import com.accenture.userservice.model.*;
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
  private final EmailFeignClient emailFeignClient;
  //calculate expiry date time
  private LocalDateTime calculateExpiryDate(int expiryTimeInMinutes) {
    return currentDateTIme().plusMinutes(userRegistrationProperties.getExpiration());
  }
  
  // get the current date time
  private LocalDateTime currentDateTIme() {
    return LocalDateTime.now();
  }
  
  public void sendEmailVerificationCode(User user) throws Exception {
    // 6 digit token generation
    int token = new Random().nextInt(100000, 999999);
    EmailVerification emailVerification = new EmailVerification();
    emailVerification.setUser(user);
    emailVerification.setToken(token);
    emailVerification.setExpiryDate(calculateExpiryDate(userRegistrationProperties.getExpiration()));
    emailVerification.setTotalAttempts(0);
    emailVerificationRepository.save(emailVerification);
    SendEmailRequest sendEmailRequest = null;//new SendEmailRequest();
    sendEmailRequest.setFromEmail("test@accenture.com");
    sendEmailRequest.setToEmail(user.getEmail());
    sendEmailRequest.setText("Token for email verification :"+token);
    // feign client for send email
    emailFeignClient.sendEmail(sendEmailRequest);
  }
  
  public EmailVerificationDto checkEmailVerification(Long userId, int requestToken) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ServiceRuntimeException("user not found for id--" + userId, ErrorCodeEnum.USER_NOT_FOUND));
    EmailVerification emailVerification = emailVerificationRepository
            .findById(userId).orElseThrow(() -> new ServiceRuntimeException("Record not found for user", ErrorCodeEnum.USER_NOT_FOUND));
    ;
    int totalAttempts = emailVerification.getTotalAttempts();
    int token = emailVerification.getToken();
    EmailVerificationDto emailVerificationDto = new EmailVerificationDto();
    // Saving total_attempts session to table
    emailVerification.setTotalAttempts(totalAttempts + 1);
    emailVerificationRepository.save(emailVerification);
    
    if(totalAttempts + 1 < userRegistrationProperties.getMaxAttempts()) {
      if(((currentDateTIme()).compareTo((emailVerification.getExpiryDate())) > 0)) {
        emailVerificationDto.setErrorCode(ErrorCodeEnum.CODE_EXPIRED);
      } else {
        if(token == requestToken) {
          user.setStatus(UserStatusEnum.ACTIVE);
          userRepository.save(user);
          emailVerificationDto.setErrorCode(ErrorCodeEnum.SUCCESS);
        } else {
          emailVerificationDto.setErrorCode(ErrorCodeEnum.TOKEN_MISMATCH);
        }
      }
    } else {
      //deleting user
      userRepository.deleteById(userId);
      emailVerificationDto.setErrorCode(ErrorCodeEnum.TOTAL_ATTEMPTS_OVER);
    }
    int remainingAttempts = userRegistrationProperties.getMaxAttempts() - emailVerification.getTotalAttempts();
    emailVerificationDto.setRemainingAttempts(remainingAttempts);
    return emailVerificationDto;
  }
}
