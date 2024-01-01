package com.accenture.userservice.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class LoginDto {
  @Email(message = "Please enter valid emailId ")
  private String username;
  private String password;
  
}