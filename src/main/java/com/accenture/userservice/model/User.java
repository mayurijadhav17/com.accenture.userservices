package com.accenture.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name = "user_details")
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotEmpty(message = "Please enter user name")
  private String name;
  @Email (message = "Please enter valid emailId ")
  private String email;
  
  @Enumerated(EnumType.ORDINAL)
  private Status status;
  
  @ManyToOne
  private Organisation organisation;
}