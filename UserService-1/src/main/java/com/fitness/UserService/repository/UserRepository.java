package com.fitness.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitness.UserService.Model.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

	boolean existsByEmail(String email);
	
	boolean existsByKeycloakId(String userId);

	User findByEmail(String email);
}
