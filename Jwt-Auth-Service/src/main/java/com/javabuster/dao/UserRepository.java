package com.javabuster.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javabuster.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
  
	public Optional<User> findByUserName(String userName);
	
	public boolean existsByEmail(String email);
	
	public boolean existsByUserName(String userName);
}
