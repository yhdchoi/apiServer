package com.yhdc.apiServer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yhdc.apiServer.model.Chat;
import com.yhdc.apiServer.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findByChat(Chat chat);
}
