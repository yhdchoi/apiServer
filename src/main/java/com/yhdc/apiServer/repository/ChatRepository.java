package com.yhdc.apiServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yhdc.apiServer.model.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

	
}
