package com.yhdc.apiServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yhdc.apiServer.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
