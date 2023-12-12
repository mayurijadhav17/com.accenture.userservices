package com.accenture.userservice.controller;

import com.accenture.userservice.dto.PostDto;
import com.accenture.userservice.restClient.ExternalAPIRestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//controller for accessing external api
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/external-api")
public class ExternalAPiRestTemplateController {
  
  private final ExternalAPIRestTemplate externalAPIRestTemplate;
  @GetMapping
  public PostDto[] getDataById() {
    return externalAPIRestTemplate.getPosts();
  }
  
}
