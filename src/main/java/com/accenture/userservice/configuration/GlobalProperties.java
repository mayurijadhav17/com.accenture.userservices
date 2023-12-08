package com.accenture.userservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//package com.accenture.userservice.configuration;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;
//
@ConfigurationProperties(prefix = "global")
@Configuration("tokenInfo")
public class GlobalProperties {
  int MAX_ATTEMPTS = 3;
 int EXPIRATION = 60 * 24;

}
