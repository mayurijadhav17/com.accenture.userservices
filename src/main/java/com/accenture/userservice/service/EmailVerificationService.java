package com.accenture.userservice.service;

import com.accenture.userservice.configuration.UserServiceGlobalProperties;
import com.accenture.userservice.dto.EmailVerificationDto;
import com.accenture.userservice.dto.SendEmailRequestDto;
import com.accenture.userservice.exception.ServiceRuntimeException;
import com.accenture.userservice.feignClient.EmailClient;
import com.accenture.userservice.model.EmailVerification;
import com.accenture.userservice.model.ErrorCodeEnum;
import com.accenture.userservice.model.User;
import com.accenture.userservice.model.UserStatusEnum;
import com.accenture.userservice.repo.EmailVerificationRepository;
import com.accenture.userservice.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.commons.httpclient.HttpClientConfiguration;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
@ImportAutoConfiguration({FeignAutoConfiguration.class, HttpClientConfiguration.class})
public class EmailVerificationService {

    private final EmailVerificationRepository emailVerificationRepository;
    private final UserRepository userRepository;
    private final UserServiceGlobalProperties userServiceGlobalProperties;
    private final EmailClient emailClient;

    //calculate expiry date time
    private LocalDateTime calculateExpiryDate(int expiryTimeInMinutes) {
        return currentDateTIme().plusMinutes(userServiceGlobalProperties.getExpiration());
    }

    // get the current date time
    private LocalDateTime currentDateTIme() {
        return LocalDateTime.now();
    }

    public void sendEmailVerificationCode(User user) throws Exception {
        // 6 digit token generation
        int token = new Random().nextInt(userServiceGlobalProperties.getToken_originValue(), userServiceGlobalProperties.getToken_boundValue());
        EmailVerification emailVerification = new EmailVerification();
        emailVerification.setUser(user);
        emailVerification.setToken(token);
        emailVerification.setExpiryDate(calculateExpiryDate(userServiceGlobalProperties.getExpiration()));
        emailVerification.setTotalAttempts(0);
        emailVerificationRepository.save(emailVerification);
        SendEmailRequestDto sendEmailRequestDto = new SendEmailRequestDto();
        sendEmailRequestDto.setFromEmail("test@accenture.com");
        sendEmailRequestDto.setToEmail(user.getEmail());
        sendEmailRequestDto.setText("Token for email verification :" + token);
        //feign client for send email
        emailClient.sendEmail(sendEmailRequestDto);
    }

    public EmailVerificationDto checkEmailVerification(Long userId, int requestToken) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ServiceRuntimeException("user not found for id--" + userId, ErrorCodeEnum.USER_NOT_FOUND));
        EmailVerification emailVerification = emailVerificationRepository
                .findById(userId).orElseThrow(() -> new ServiceRuntimeException("Record not found for user", ErrorCodeEnum.USER_NOT_FOUND));

        int totalAttempts = emailVerification.getTotalAttempts();
        int token = emailVerification.getToken();
        EmailVerificationDto emailVerificationDto = new EmailVerificationDto();
        // Saving total_attempts session to table
        emailVerification.setTotalAttempts(totalAttempts + 1);
        emailVerificationRepository.save(emailVerification);

        if (totalAttempts + 1 < userServiceGlobalProperties.getMaxAttempts()) {
            if (((currentDateTIme()).compareTo((emailVerification.getExpiryDate())) > 0)) {
                emailVerificationDto.setErrorCode(ErrorCodeEnum.CODE_EXPIRED);
            } else {
                if (token == requestToken) {
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
        int remainingAttempts = userServiceGlobalProperties.getMaxAttempts() - emailVerification.getTotalAttempts();
        emailVerificationDto.setRemainingAttempts(remainingAttempts);
        return emailVerificationDto;
    }
}
