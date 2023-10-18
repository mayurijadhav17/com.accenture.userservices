package com.accenture.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.userservice.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
