package com.accenture.userservice.feignClient;

import com.accenture.userservice.model.Email;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "email-service", url = "http://localhost:8081", path = "/email-service")
public interface EmailClient {
  @GetMapping("/email-service/{token}")
   Email sendEmail(@PathVariable("token") int token);
}
