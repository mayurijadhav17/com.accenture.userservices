package com.accenture.userservice.feignClient;

import com.accenture.userservice.model.SendEmailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "email-service", url = "http://localhost:8081", path = "/email-service")
public interface EmailClient {
  @GetMapping("/email-service")
  SendEmailRequest sendEmail();

}
