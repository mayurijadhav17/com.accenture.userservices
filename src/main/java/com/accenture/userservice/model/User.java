package com.accenture.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "user_details")
@NoArgsConstructor
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotEmpty(message = "Please enter user name")
  private String name;
  @Email(message = "Please enter valid emailId ")
  private String email;
  @Enumerated(EnumType.STRING)
  private UserStatusEnum status;
  @JsonIgnore
  @ManyToOne
  private Organisation organisation;
  
}