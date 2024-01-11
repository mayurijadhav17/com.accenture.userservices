package com.accenture.userservice.feignClient;

import com.accenture.userservice.dto.SendEmailRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "emailFeignClient", url = "http://localhost:8082")
public interface EmailClient {
    @PostMapping("/email/send")
   void sendEmail(SendEmailRequestDto sendEmailRequestDto);

}