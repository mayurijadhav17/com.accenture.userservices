package com.accenture.userservice.model;

import lombok.Data;

@Data
public class SendEmailRequest {
  private String emailId;
  private String subject;
  private String text;
}