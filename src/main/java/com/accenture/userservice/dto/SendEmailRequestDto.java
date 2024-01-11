package com.accenture.userservice.dto;

import lombok.Data;

@Data
public class SendEmailRequestDto {
   String fromEmail;
   String toEmail;
   String subject;
   String text;
}