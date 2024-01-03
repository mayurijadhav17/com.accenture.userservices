package com.accenture.userservice.repo;

import com.accenture.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  
  boolean existsUserByEmail(String email);
  //Optional<User> findByUsername(String username);
  Optional<User> findByEmail(String email);
  
}
