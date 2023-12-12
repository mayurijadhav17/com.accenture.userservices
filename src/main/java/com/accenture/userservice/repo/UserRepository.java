package com.accenture.userservice.repo;

import com.accenture.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  
  boolean existsUserByEmail(String email);
  
  User findByEmail(String email);
  
}
