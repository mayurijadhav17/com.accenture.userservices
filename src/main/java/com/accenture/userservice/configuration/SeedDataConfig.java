package com.accenture.userservice.configuration;

import com.accenture.userservice.model.User;
import com.accenture.userservice.model.UserRoleEnum;
import com.accenture.userservice.repo.UserRepository;
import com.accenture.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {
  
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserService userService;
  
  @Override
  public void run(String... args) throws Exception {
    
    if(userRepository.count() == 0) {
      
      User admin = User
              .builder()
              .name("admin")
              .email("admin@admin.com")
              .password(passwordEncoder.encode("password"))
              .role(UserRoleEnum.ROLE_ADMIN)
              .build();
      
      userService.createUser(admin);
      log.info("created ADMIN user - {}", admin);
    }
  }
  
}