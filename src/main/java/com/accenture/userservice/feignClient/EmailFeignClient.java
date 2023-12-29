package com.accenture.userservice.feignClient;

import com.accenture.userservice.dto.SendEmailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "email/send" )
public interface EmailFeignClient {
  @PostMapping("/email/send")
    void sendEmail(SendEmailRequest sendEmailRequest);

}
