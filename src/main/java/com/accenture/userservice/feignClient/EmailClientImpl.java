package com.accenture.userservice.feignClient;

import com.accenture.userservice.model.Email;

public class EmailClientImpl implements EmailClient{
  
  @Override
  public Email sendEmail(int token) {
    Email email = new Email();
    email.setSubject("Email Verification");
    email.setText("Token :"+ token);
    return email;
  }
}
