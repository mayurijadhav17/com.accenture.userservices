package com.accenture.userservice.repo;

import com.accenture.userservice.model.Role;
import com.accenture.userservice.model.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(UserRoleEnum name);
}
