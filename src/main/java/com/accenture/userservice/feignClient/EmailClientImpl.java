package com.accenture.userservice.feignClient;

import com.accenture.userservice.model.SendEmailRequest;

public class EmailClientImpl implements EmailClient{
  
  @Override
  public SendEmailRequest sendEmail() {
    SendEmailRequest email = new SendEmailRequest();
    email.setSubject("Email Verification");
   return email;
  }

}
