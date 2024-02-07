package com.accenture.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendEmailRequestDto {
   String fromEmail;
   String toEmail;
   String subject;
   String text;
}