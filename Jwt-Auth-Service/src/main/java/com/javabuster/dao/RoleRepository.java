package com.javabuster.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javabuster.model.Role;
import com.javabuster.model.Roles;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
  
	Optional<Role> findByRoleName(Roles role);
}
