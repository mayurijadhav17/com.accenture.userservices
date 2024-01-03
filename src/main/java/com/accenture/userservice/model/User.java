package com.accenture.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Data
@Builder

@AllArgsConstructor
@Entity
@ToString
@Table(name = "user_details")
@RequiredArgsConstructor
public class User  {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NotEmpty(message = "Please enter user name")
  private String username;
  
  @Email(message = "Please enter valid emailId ")
  private String email;
  
  @Enumerated(EnumType.STRING)
  private UserStatusEnum status;
  
  private String password;
  
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Set<UserRoleEnum> role;
  
  @JsonIgnore
  @ManyToOne
  private Organisation organisation;

}