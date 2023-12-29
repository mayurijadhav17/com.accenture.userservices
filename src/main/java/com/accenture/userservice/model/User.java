package com.accenture.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder

@AllArgsConstructor
@Entity
@ToString
@Table(name = "user_details")
@RequiredArgsConstructor
public class User implements UserDetails {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NotEmpty(message = "Please enter user name")
  private String name;
  
  @Email(message = "Please enter valid emailId ")
  private String email;
  
  @Enumerated(EnumType.STRING)
  private UserStatusEnum status;
  
  private String password;
  
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private UserRoleEnum role;
  
  @JsonIgnore
  @ManyToOne
  private Organisation organisation;
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }
  
  @Override
  public String getUsername() {
    return email;
  }
  
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  
  @Override
  public boolean isEnabled() {
    return true;
  }
}