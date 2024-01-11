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
  int token_originValue =100000;
  int token_boundValue=999999;

}
