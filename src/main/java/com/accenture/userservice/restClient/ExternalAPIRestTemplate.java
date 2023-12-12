package com.accenture.userservice.restClient;

import com.accenture.userservice.dto.PostDto;
import com.accenture.userservice.exception.RestTemplateErrorHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExternalAPIRestTemplate {
  
  private final RestTemplate restTemplate;
  private final RestTemplateBuilder restTemplateBuilder;
  private final String api_url = "https://jsonplaceholder.typicode.com/posts/";
  
  public PostDto[] getPosts() {
    RestTemplate restTemplate = restTemplateBuilder
            .errorHandler(new RestTemplateErrorHandler())
            .build();
    PostDto[] postList = restTemplate.getForObject(api_url, PostDto[].class);
    return postList;
  }
}