package com.accenture.userservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "user-registration")
@Component
@Data
public class UserServiceGlobalProperties {
    int maxAttempts;
    int expiration;
    int tokenSize;
}
