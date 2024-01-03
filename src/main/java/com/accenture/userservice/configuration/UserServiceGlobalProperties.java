package com.accenture.userservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "user-registration")
@Component
@Data
public class UserServiceGlobalProperties {
  int maxAttempts = 3;
  int expiration = 1440;
  String jwtSecret = "======================userServiceKey======================";
  int jwtExpirationMs = 86400000;
  String jwtCookie = "user-jwt-token";
}
