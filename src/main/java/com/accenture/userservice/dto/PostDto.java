package com.accenture.userservice.dto;

import lombok.Data;
//Dto for accessing external api https://jsonplaceholder.typicode.com/posts/
@Data
public class PostDto {
  private int userId;
  private String title;
  private String body;
}