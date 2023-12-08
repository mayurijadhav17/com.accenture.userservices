package com.accenture.userservice.service;

import com.accenture.userservice.exception.ResourceNotFoundException;
import com.accenture.userservice.model.EmailVerification;
import com.accenture.userservice.model.User;
import com.accenture.userservice.repo.EmailVerificationRepository;
import com.accenture.userservice.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailVerificationService {

private final EmailVerificationRepository emailVerificationRepository;
private final UserRepository userRepository;

private static final int MAX_ATTEMPTS = 3;
private static final int EXPIRATION = 60 * 24;

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
	//token generation
	String authCode = UUID.randomUUID().toString();
	EmailVerification emailVerification = new EmailVerification();
	emailVerification.setUserId(user.getId());
	emailVerification.setToken(authCode);
	emailVerification.setExpiryDate(calculateExpiryDate(EXPIRATION));
	emailVerification.setTotalAttempts(0);
	emailVerificationRepository.save(emailVerification);
	log.info("Email confirmation Code-------------->" + authCode);
}

public String checkEmailVerification(Long userId, String requestToken) throws Exception {
	EmailVerification emailVerification = emailVerificationRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("User Not found !"));
	Integer totalAttempts = emailVerification.getTotalAttempts();
	String token = emailVerification.getToken();
	
	// Saving total_attempts session to table
	emailVerification.setTotalAttempts(totalAttempts + 1);
	emailVerificationRepository.save(emailVerification);
	
	if(totalAttempts + 1 >= MAX_ATTEMPTS) {
		//deleting user
		userRepository.deleteById(userId);
		throw new Exception("Email Verification 3 attempts Over ,User record deleted!!");
	}
	if((currentDateTIme()).compareTo((emailVerification.getExpiryDate())) > 0) {
		throw new Exception("Sorry,your token is Expired !!");
	}
	if(!token.equals(requestToken)) {
		throw new Exception("Verification code is not matching !!");
	}
	return "Email successfully verified!!";
}
}
