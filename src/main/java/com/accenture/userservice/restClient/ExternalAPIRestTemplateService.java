package com.accenture.userservice.restClient;

import com.accenture.userservice.configuration.RestTemplateConfig;
import com.accenture.userservice.dto.PostDto;
import com.accenture.userservice.exception.RestTemplateErrorHandler;
import com.accenture.userservice.exception.ServiceUnAvailableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExternalAPIRestTemplateService {
  
  private final RestTemplate restTemplate;
  private final RestTemplateBuilder restTemplateBuilder;
  private final RestTemplateConfig restTemplateConfig;
  
  public PostDto[] getPosts() {
    RestTemplate restTemplate = restTemplateBuilder
            .errorHandler(new RestTemplateErrorHandler())
            .build();
    try {
      
      PostDto[] postList = restTemplate.getForObject(restTemplateConfig.url, PostDto[].class);
      return postList;
    } catch (RuntimeException e) {
      
      throw new ServiceUnAvailableException("Fail to fetch data from external api");
    }
    
  }
}