package com.accenture.userservice.dto;

import lombok.Data;

@Data
public class SendEmailRequest {
   String fromEmail;
   String toEmail;
   String subject;
   String text;
}