package com.accenture.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.userservice.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

boolean existsUserByEmail(String email);

Optional<User> findById(Long id);

boolean existsById(Long id);

void deleteById(Long id);

}
