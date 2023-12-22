package com.accenture.userservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendEmailRequest {
  private String fromEmail;
  private String toEmail;
  private String subject;
  private String text;
}