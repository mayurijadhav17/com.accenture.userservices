package com.accenture.userservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "user-security")
@Component
@Data
public class JwtConfigurationProperties {

    String jwtSecret ;
    int jwtExpirationMs = 900000;
    String jwtCookie = "user-jwt-token";
}


