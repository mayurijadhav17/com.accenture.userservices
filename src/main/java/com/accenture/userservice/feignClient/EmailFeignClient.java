package com.accenture.userservice.feignClient;

import com.accenture.userservice.model.SendEmailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "email-service", url = "http://localhost:8080", path = "/email")
public interface EmailFeignClient {
  @PostMapping("/email/send")
    void sendEmail(SendEmailRequest sendEmailRequest);

}
