package com.accenture.userservice.repo;

import com.accenture.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  
  boolean existsUserByEmail(String email);
  
  Optional<User> findById(Long id);
  
  User findByEmail(String email);
  
  boolean existsById(Long id);
  
  void deleteById(Long id);
  
  void deleteByEmail(String email);
}
